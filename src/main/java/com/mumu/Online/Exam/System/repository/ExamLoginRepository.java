package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamLoginRepository extends JpaRepository<ExamLogin, Long> {

    Page<ExamLogin> findAll(Specification<ExamLogin> spec, Pageable pageable);

    Optional<ExamLogin> findByCustomerAndId(String customer, Long id);
}
