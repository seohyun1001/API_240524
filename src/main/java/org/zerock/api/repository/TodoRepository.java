package org.zerock.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
