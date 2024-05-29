package org.zerock.api.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.api.dto.PageRequestDTO;
import org.zerock.api.dto.PageResponseDTO;
import org.zerock.api.dto.TodoDTO;

@Transactional
public interface TodoService {
    // 게시글 등록
    Long register(TodoDTO todoDTO);
    // 게시글 조회
    TodoDTO read(Long tno);
    // 서비스 계층 구현
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);

    // 게시글 수정
    void modify(TodoDTO todoDTO);
    // 게시글 삭제
    void remove(Long tno);
}
