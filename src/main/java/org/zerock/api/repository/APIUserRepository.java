package org.zerock.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api.domain.APIUser;

public interface APIUserRepository extends JpaRepository<APIUser,  String> {
}
