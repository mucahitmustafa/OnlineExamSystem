package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByCustomer(String customer);

    Optional<Student> findByCustomerAndId(String customer, Long id);

}
