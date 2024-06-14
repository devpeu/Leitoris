package com.nassau.leitoris.repositories;

import com.nassau.leitoris.models.LoanModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {

    List<LoanModel> findByFkUserIdAndEntregueFalse(Long userId);
}
