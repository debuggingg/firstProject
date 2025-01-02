package springboot.webproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    /* 메인페이지 */
    @GetMapping("/")
    public String mainHome() {
        return "index";
    }
//    @GetMapping("/admin")
//    public String adminPage() {
//        return "admin"; // ROLE_ADMIN 권한 필요
//    }
//
//    @GetMapping("/user")
//    public String userPage() {
//        return "user"; // ROLE_USER 권한 필요
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login"; // 로그인한 사용자만 접근 가능
//    }
}
