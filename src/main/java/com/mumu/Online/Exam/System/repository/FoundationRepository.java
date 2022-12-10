package com.mumu.Online.Exam.System.repository;

import com.mumu.Online.Exam.System.model.entity.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoundationRepository extends JpaRepository<Foundation, Long> {

    Optional<Foundation> findByCustomer(String customer);

}
