package springboot.webproject.service;

import springboot.webproject.dto.CartDTO;
import springboot.webproject.dto.UsersDTO;

import java.util.Optional;

public interface CartService {

    // 장바구니에 상품 추가
    void addProductToCart(UsersDTO usersId, long prodNo, int quantity) throws Exception;

    // 장바구니에서 상품 주문 완료 처리
    void completeOrder(int cartNo) throws Exception;

    // 장바구니에서 주문 완료된 상품만 가져오기
    Optional<CartDTO> getCompletedOrders(String userId);
}