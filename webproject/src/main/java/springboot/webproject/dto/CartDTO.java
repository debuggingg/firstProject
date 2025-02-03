package springboot.webproject.dto;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import springboot.webproject.entity.ProductEntity;

/*
 CREATE TABLE cartlist (
    cart_no INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 기본키, 자동 증가
    cart_users_id VARCHAR(20), -- 사용자 ID (외래키 가능)
    cart_quantity INT, -- 수량
    cart_prod_no INT, -- 상품 번호 (외래키 가능)
    status INT NOT NULL DEFAULT 0, -- 상태 (기본값: 0)
    FOREIGN KEY (cart_users_id) REFERENCES users(user_id), -- users 테이블의 user_id와 연결 (필요하면 추가)
    FOREIGN KEY (cart_prod_no) REFERENCES product(prod_no) -- product 테이블의 prod_no와 연결 (필요하면 추가)
);*/


@Entity
@Table(name = "cartlist")//
@Getter
@Setter
public class CartDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CART_USERS_ID",referencedColumnName = "USERS_ID")
    private UsersDTO users;
    //    private String cartusersId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CART_PROD_NO",referencedColumnName = "PROD_NO")
    private ProductEntity product;
    //    private int cartproductNo;
    private int cartQuantity;

    // 장바구니 상태 (0: 장바구니, 1: 주문완료)
    private int status;

    public CartDTO() {
    }

    public CartDTO(int cartNo, UsersDTO users, ProductEntity product, int cartQuantity, int status) {
        this.cartNo = cartNo;
        this.users = users;
        this.product = product;
        this.cartQuantity = cartQuantity;
        this.status = status;
    }
}