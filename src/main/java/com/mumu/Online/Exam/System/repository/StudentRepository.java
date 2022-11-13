package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
