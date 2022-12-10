package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findAllByCustomer(String customer);

    Optional<Exam> findByCustomerAndId(String customer, Long id);
}
