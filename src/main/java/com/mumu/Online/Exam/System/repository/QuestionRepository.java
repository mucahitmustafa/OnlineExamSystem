package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByCustomer(String customer);

    Optional<Question> findByCustomerAndId(String customer, Long id);

}
