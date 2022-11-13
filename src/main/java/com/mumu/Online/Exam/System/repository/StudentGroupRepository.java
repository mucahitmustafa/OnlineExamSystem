package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

}
