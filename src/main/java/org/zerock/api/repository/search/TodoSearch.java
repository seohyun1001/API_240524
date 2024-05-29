package org.zerock.api.repository.search;

import org.springframework.data.domain.Page;
import org.zerock.api.dto.PageRequestDTO;
import org.zerock.api.dto.TodoDTO;

public interface TodoSearch {
    Page<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
