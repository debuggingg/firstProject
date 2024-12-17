package springboot.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.webproject.dto.UsersDTO;
import springboot.webproject.repository.UsersRepository;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Transactional
    public UsersDTO createUser(UsersDTO user) {
        return usersRepository.save(user); // Insert user into the database
    }
}

