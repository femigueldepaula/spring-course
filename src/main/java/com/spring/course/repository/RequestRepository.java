package com.spring.course.repository;

import com.spring.course.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByOwnerId(Long id);
}
