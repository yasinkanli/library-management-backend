package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.LoanRequestDto;
import org.yasinkanli.librarymanagement.dto.LoanResponseDto;

import java.util.List;

public interface LoanService {
    LoanResponseDto createLoan(LoanRequestDto dto);
    LoanResponseDto getById(Long id);
    List<LoanResponseDto> listAll();
    List<LoanResponseDto> listByMember(Long memberId);
    void delete(Long id);
}