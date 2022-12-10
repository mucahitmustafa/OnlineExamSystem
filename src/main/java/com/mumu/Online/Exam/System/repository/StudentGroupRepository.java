package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    List<StudentGroup> findAllByCustomer(String customer);

    Optional<StudentGroup> findByCustomerAndId(String customer, Long id);

}
