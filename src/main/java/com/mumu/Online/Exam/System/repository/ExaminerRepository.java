package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExaminerRepository extends JpaRepository<Examiner, Long> {

    List<Examiner> findAllByCustomer(String customer);

    Optional<Examiner> findByCustomerAndId(String customer, Long id);

}
