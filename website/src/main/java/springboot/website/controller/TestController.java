package springboot.website.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.website.entity.TestEntity;
import springboot.website.service.TestService;

import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public List<TestEntity> getAllEntities() {
        return testService.getAllEntities();
    }

    @PostMapping("/test")
    public TestEntity addEntity(@RequestParam String name) {
        return testService.saveEntity(name);
    }
}