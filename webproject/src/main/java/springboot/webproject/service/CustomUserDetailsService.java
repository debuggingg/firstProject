package springboot.webproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.webproject.dto.UsersDTO;

import java.util.Optional;
/* 원하는 인증에서 사용 되는 이름이나 그런것들을 하기위해서 customize 하기 위해 위해 사용 되는 클라스
예를 들어 username-> userId 으로 변경 하고싶을때*/
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService; // UsersDTO 관련 서비스

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersDTO> userOptional = userService.loginId(username); // 사용자 조회
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        UsersDTO user = userOptional.get();

        // UserDetails 객체 생성 및 반환
        return User.builder()
                .username(user.getUsersId())
                .password(user.getUsersPw()) // Spring Security에서 비밀번호는 암호화 필요
                .roles("USER") // 사용자 권한 설정
                .build();
    }
}