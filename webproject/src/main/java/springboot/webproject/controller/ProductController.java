package springboot.webproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import springboot.webproject.dto.ProductDTO;
import springboot.webproject.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    //coffeebean list 페이지
    @GetMapping("/coffeebean/list")
    public String coffeebeanList(Model model){
        List<ProductDTO> coffeeBeans = productService.findProductsByType(1); // 타입이 1인 제품만 가져옴
        int countProduct=coffeeBeans.size();

        model.addAttribute("productList", coffeeBeans);
        model.addAttribute("countProduct", countProduct);
        return "/view/product/coffeebean_list"; // 커피원두 리스트 페이지 경로
    }

    //coffeebean view 페이지
    @GetMapping("/coffeebean/view/{prodNo}")
    public String coffeebeanView(@PathVariable("prodNo") Long prodNo, Model model) {
        ProductDTO product = productService.findProductById(prodNo);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다.");
        }
        model.addAttribute("product", product);
        return "/view/product/coffeebean_view";
    }

    //coldbrew list 페이지
    @GetMapping("/coldbrew/list")
    public String coldbrewList(Model model){
        List<ProductDTO> coffeeBeans = productService.findProductsByType(2); // 타입이 1인 제품만 가져옴
        int countProduct=coffeeBeans.size();

        model.addAttribute("productList", coffeeBeans);
        model.addAttribute("countProduct", countProduct);

        return "/view/product/coldbrew_list";
    }




}
