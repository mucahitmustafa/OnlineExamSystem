package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Question;
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
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    Optional<Question> findByCustomerAndId(String customer, Long id);

    List<Question> findByExamId(Long examId);

    void deleteByExamId(Long examId);
}
