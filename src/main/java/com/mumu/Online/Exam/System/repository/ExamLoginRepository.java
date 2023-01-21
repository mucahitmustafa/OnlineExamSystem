package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.ExamLogin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ExamLoginRepository extends JpaRepository<ExamLogin, Long> {

    Page<ExamLogin> findAll(Specification<ExamLogin> spec, Pageable pageable);

    Optional<ExamLogin> findByCustomerAndId(String customer, Long id);

    List<ExamLogin> findByStudent_Id(Long studentId);

    void deleteByStudent_Id(Long studentId);

    void deleteByExam_Id(Long examId);

    List<ExamLogin> findAllByExamId(Long examId);
}
