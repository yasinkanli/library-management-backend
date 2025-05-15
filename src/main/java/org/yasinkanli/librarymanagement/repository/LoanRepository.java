package org.yasinkanli.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yasinkanli.librarymanagement.entity.Loan;


import java.util.List;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Long countByMemberId(Long memberId);

    List<Loan> findByMemberId(Long memberId);
}
