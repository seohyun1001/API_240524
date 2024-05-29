package org.zerock.api.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.api.dto.TodoDTO;

@Transactional
public interface TodoService {
    Long register(TodoDTO todoDTO);
    TodoDTO read(Long tno);
}
