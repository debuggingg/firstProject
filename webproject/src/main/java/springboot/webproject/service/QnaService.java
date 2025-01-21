package springboot.webproject.service;

import org.springframework.data.domain.Page;
import springboot.webproject.dto.NoticeDTO;
import springboot.webproject.dto.QnaDTO;

import java.util.Optional;

public interface QnaService {
    //기본 목록과 페이징 처리 하는 메소드
    Page<QnaDTO> getActiveQna(int qnaNo, int pageNum, int pageSize);
    // 디테일을 보는 메소드
    Optional<QnaDTO> getQnaDetail(int qnaNo);
}
