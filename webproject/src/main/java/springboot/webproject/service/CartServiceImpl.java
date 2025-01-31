package springboot.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.webproject.dto.CartDTO;
import springboot.webproject.dto.ProductDTO;
import springboot.webproject.dto.UsersDTO;
import springboot.webproject.repository.CartRepository;
import org.springframework.transaction.annotation.Transactional;
import springboot.webproject.repository.ProductRepository;

import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public void addProductToCart(UsersDTO usersId, int prodNo, int quantity) throws Exception {
    ProductDTO productDTO = productRepository.findByProdNo(prodNo)
            .orElseThrow(() -> new Exception("product not found"));
    if(productDTO.getProdAmount() < quantity){
        throw new Exception("not enough stock");
    }
    CartDTO cartDTO=new CartDTO();
    cartDTO.setUsers(usersId);// 사용자 ID 설정
        System.out.println("usersId= "+usersId);
        cartDTO.setProductDTO(productDTO);
        cartDTO.setProductDTO(productDTO); // 상품 정보 설정
        cartDTO.setCartQuantity(quantity); // 수량 설정
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
        return cartRepository.findAllByUsersDTO_UsersIdAndStatus(userId, 1);
    }
}
