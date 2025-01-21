package springboot.webproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.webproject.dto.QnaAnswer;

public interface QnaAnswerRepository extends JpaRepository<QnaAnswer,Long> {
}
