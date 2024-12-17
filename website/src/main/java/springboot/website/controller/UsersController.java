package springboot.website.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springboot.website.dto.UsersDTO;
import springboot.website.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    // Serve the HTML form for user creation
    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new UsersDTO());
        return "create_user"; // This maps to create_user.html
    }

    // Handle form submission
    @PostMapping
    public ModelAndView createUser(@ModelAttribute UsersDTO user) {
        userService.createUser(user); // Save the user using the service
        return new ModelAndView("redirect:/users/create?success=true");
    }
}
