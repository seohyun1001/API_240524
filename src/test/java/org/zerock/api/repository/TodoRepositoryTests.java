package org.zerock.api.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api.domain.Todo;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    // tbl_todo_api에 데이터 넣기
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Todo todo = Todo.builder()
                    .title("Todo---" + i)
                    .dueDate(LocalDate.of(2024, (i%12)+1, (i%30)+1))
                    .writer("user" + (i % 10))
                    .complete(false)
                    .build();

            todoRepository.save(todo);
        });
    }

}
