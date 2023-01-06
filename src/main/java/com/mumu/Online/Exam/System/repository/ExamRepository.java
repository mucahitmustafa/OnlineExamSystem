package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    Page<Exam> findAll(Specification<Exam> spec, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM exam e WHERE id in (SELECT es.exam_id FROM exam_students es WHERE es.student_id = ?1)")
    List<Exam> findByStudent(Long studentId);

    Optional<Exam> findByCustomerAndId(String customer, Long id);

    void deleteByCustomerAndId(String customer, Long id);
}
