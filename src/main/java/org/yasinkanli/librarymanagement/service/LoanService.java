package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.LoanDto;
import org.yasinkanli.librarymanagement.dto.LoanDto;

import java.util.List;

public interface LoanService {
    LoanDto createLoan(LoanDto dto);
    LoanDto getById(Long id);
    List<LoanDto> listAll();
    List<LoanDto> listByMember(Long memberId);
    void delete(Long id);
}