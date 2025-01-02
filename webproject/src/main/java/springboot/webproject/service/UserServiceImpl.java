package springboot.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.webproject.dto.UsersDTO;
import springboot.webproject.repository.UsersRepository;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UsersDTO createUser(UsersDTO user) {

        return usersRepository.save(user); // 회원 정보 저장
    }


    @Override
//    public Optional<UsersDTO> login(String usersId, String usersPw) {
//        //Spring Security에서는 비밀번호를 PasswordEncoder로 암호화하여 저장하고, 로그인 시 입력된 비밀번호와 저장된 암호화된 비밀번호를 비교해야 합니다.
//        return usersRepository.findByUsersIdAndUsersPw(usersId, usersPw); // 로그인 처리
//    }
    public Optional<UsersDTO> login(String usersId, String usersPw) {
        Optional<UsersDTO> userOptional = usersRepository.findByUsersId(usersId);
        if (userOptional.isPresent() && passwordEncoder.matches(usersPw, userOptional.get().getUsersPw())) {
            String encryptedPassword = userOptional.get().getUsersPw(); // 데이터베이스에 저장된 암호화된 비밀번호
            if (passwordEncoder.matches(usersPw, encryptedPassword)) {
                System.out.println("비밀번호 일치");
                return userOptional; // 로그인 성공
            } else {
                System.out.println("비밀번호 불일치");
            }
        } else {
            System.out.println("사용자 ID 없음");
        }
        return Optional.empty(); // 로그인 실패

        }
//            return userOptional;
//        }
//        return Optional.empty();
//
//
//    }


    @Override
    public Optional<UsersDTO> loginId(String usersId) {
        return usersRepository.findByUsersId(usersId);
    }
}
