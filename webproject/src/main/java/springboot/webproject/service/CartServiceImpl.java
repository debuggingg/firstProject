package springboot.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.webproject.dto.CartDTO;
import springboot.webproject.dto.ProductDTO;
import springboot.webproject.dto.UsersDTO;
import springboot.webproject.entity.ProductEntity;
import springboot.webproject.repository.CartRepository;
import org.springframework.transaction.annotation.Transactional;
import springboot.webproject.repository.ProductRepository;
import springboot.webproject.repository.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProductRepository productRepository;




    @Override
    public void addProductToCart(UsersDTO usersId, long prodNo, int cartQuantity) throws Exception {
        ProductEntity productEntity= productRepository.findByProdNo(prodNo)
            .orElseThrow(() -> new Exception("product not found")) ;
    if(productEntity.getProdAmount() < cartQuantity){
        throw new Exception("not enough stock");
    }
    CartDTO cartDTO=new CartDTO();
    cartDTO.setUsers(usersId);// 사용자 ID 설정
        System.out.println("usersId= "+usersId);
//        cartDTO.setProductDTO(productDTO);
        cartDTO.setProduct(productEntity); // 상품 정보 설정
        cartDTO.setCartQuantity(cartQuantity); // 수량 설정
        cartDTO.setStatus(0); // 기본 상태는 장바구니 상태 (0)

        cartRepository.save(cartDTO);
    }

    @Override
    public void completeOrder(int cartNo) throws Exception {
        CartDTO cartDTO = cartRepository.findByCartNo(cartNo);
        if (cartDTO == null) {
            throw new Exception("Cart item not found");
        }

        // 주문 완료 처리
        cartDTO.setStatus(1); // 주문 완료 상태로 변경
        cartRepository.save(cartDTO); // 상태 업데이트
    }

    @Override
    public Optional<CartDTO> getCompletedOrders(String userId) {
        return cartRepository.findAllByUsers_UsersIdAndStatus(userId, 1);
    }
}
