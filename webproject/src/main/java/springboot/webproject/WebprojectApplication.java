package springboot.webproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EntityScan(basePackages = {"springboot.webproject.dto"})// 이거를 해줘야 자동으로 테이블이 생성되게 하는 경로가 연결 된다
@ComponentScan(basePackages = "springboot.webproject")
public class WebprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebprojectApplication.class, args);
	}

}
