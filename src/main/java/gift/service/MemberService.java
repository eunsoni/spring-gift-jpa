package gift.service;

import gift.dto.MemberDto;
import gift.entity.Member;
import gift.repository.MemberRepository;
import gift.util.JwtTokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public MemberService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public String registerMember(MemberDto memberDto) {
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());
        Member newMember = new Member(memberDto.getEmail(), encodedPassword);
        memberRepository.save(newMember);
        return jwtTokenProvider.createToken(memberDto.getEmail());
    }

    public String login(String email, String password) {
        Member member = memberRepository.findByEmail(email);

        // 비밀번호 검증
        if (member != null && passwordEncoder.matches(password, member.getPassword())) {
            return jwtTokenProvider.createToken(email);
        }
        throw new RuntimeException("Invalid email or password");
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
