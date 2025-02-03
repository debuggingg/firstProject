package springboot.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springboot.webproject.dto.CartDTO;
import springboot.webproject.dto.UsersDTO;
import springboot.webproject.service.CartService;
import springboot.webproject.service.UserService;

@Controller
@RequestMapping("/user")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private final UserService userService;

    public CartController(UserService userService) {
        this.userService = userService;
    }

    // 장바구니에 상품 추가
    @PostMapping("/cart/add")
    public String addProductToCart(Authentication authentication,
                                   @RequestParam("prodNo") long prodNo,
                                   @RequestParam("cartQuantity") int cartQuantity,
                                   RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Received prodNo: " + prodNo);
            System.out.println("Received quantity: " + cartQuantity);
            String username = authentication.getName();
            UsersDTO user = userService.findUserByUsersId(username);
            System.out.println("username= "+ user);
            cartService.addProductToCart(user, prodNo, cartQuantity);
            redirectAttributes.addFlashAttribute("message", "장바구니에 추가되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "추가 실패: " + e.getMessage());
        }
        return "redirect:/product/coffeebean/list";
    }

    // 장바구니 목록 조회
    @GetMapping("/list")
    public String viewCart(@RequestParam String userId, Model model) {
//        model.addAttribute("cartList", cartService.getCartList(userId));

        return "view/product/cart_list"; // cart_list.html을 보여줌
    }
}
