package gift;

import gift.entity.Member;
import gift.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepositoryTest {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Test
    void saveMember() {
        // given
        Member member = new Member("test@example.com", "password123");
        
        // when
        Member savedMember = memberRepository.save(member);
        
        // then
        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getEmail()).isEqualTo("test@example.com");
    }
    
    @Test
    void findByEmail() {
        // given
        Member member = new Member("test@example.com", "password123");
        memberRepository.save(member);
        
        // when
        Member foundMember = memberRepository.findByEmail("test@example.com");
        
        // then
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getEmail()).isEqualTo("test@example.com");
    }
}
