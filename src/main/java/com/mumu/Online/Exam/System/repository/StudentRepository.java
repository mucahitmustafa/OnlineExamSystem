package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Specification<Student> spec, Pageable pageRequest);

    Optional<Student> findByCustomerAndId(String customer, Long id);

    Optional<Student> findByMailAndPasswordAndVerifiedTrue(String mail, String password);

    boolean existsByMail(String mail);

    boolean existsByNumberAndCustomer(String number, String customer);
}
