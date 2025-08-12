package gift.service;

import gift.entity.Member;
import gift.entity.Product;
import gift.entity.Wishlist;
import gift.repository.MemberRepository;
import gift.repository.ProductRepository;
import gift.repository.WishlistRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public WishlistService(WishlistRepository wishlistRepository, 
                          MemberRepository memberRepository, 
                          ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
    }

    public Page<Product> getWishlistByEmail(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Wishlist> wishlist = wishlistRepository.findByMemberEmail(email, pageable);
        return wishlist.map(Wishlist::getProduct);
    }

    @Transactional
    public void addWishlistItem(String email, Long productId) {
        Member member = memberRepository.findByEmail(email);
        Product product = productRepository.findById(productId).orElseThrow();
        
        // 이미 위시리스트에 있는지 확인
        Wishlist existingWish = wishlistRepository.findByMemberEmailAndProductId(email, productId);
        if (existingWish == null) {
            Wishlist wishlist = new Wishlist(member, product);
            wishlistRepository.save(wishlist);
        }
    }

    @Transactional
    public void deleteWishlistItem(String email, Long productId) {
        Wishlist wish = wishlistRepository.findByMemberEmailAndProductId(email, productId);
        if (wish != null) {
            wishlistRepository.delete(wish);
        }
    }
}
