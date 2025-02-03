package springboot.webproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.webproject.dto.CartDTO;
import springboot.webproject.dto.UsersDTO;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartDTO, Integer> {
    // CartDTO를 cartNo로 찾기
    CartDTO findByCartNo(int cartNo);

    // 특정 사용자(userId)와 특정 상품(productNo)에 해당하는 장바구니 항목 찾기
    CartDTO findByUsers_UsersIdAndProduct_ProdNo(UsersDTO usersId, int prodNo);

    Optional<CartDTO> findAllByUsers_UsersIdAndStatus(String userId, int i);
}