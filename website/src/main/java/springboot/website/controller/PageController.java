package springboot.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping("/users")
    public String mainPage(Model model) {
        model.addAttribute("workgroup", "main");
        model.addAttribute("work", "main_page");
        return "index";
    }

    @GetMapping("/admin")
    public String adminPage(@RequestParam(defaultValue = "admin") String workgroup,
                            @RequestParam(defaultValue = "manage_users") String work,
                            Model model) {
        model.addAttribute("workgroup", workgroup);
        model.addAttribute("work", work);
        return "admin_page"; // 관리자 페이지 템플릿
    }
}