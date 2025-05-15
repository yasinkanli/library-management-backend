package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.LoanRequestDto;
import org.yasinkanli.librarymanagement.dto.LoanResponseDto;
import org.yasinkanli.librarymanagement.service.LoanService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<LoanResponseDto>> createLoan(@Valid @RequestBody LoanRequestDto dto) {
        LoanResponseDto created = loanService.createLoan(dto);
        ApiResponse<LoanResponseDto> response = new ApiResponse<>(true, "Loan created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LoanResponseDto>> getLoanById(@PathVariable Long id) {
        LoanResponseDto loan = loanService.getById(id);
        ApiResponse<LoanResponseDto> response = new ApiResponse<>(true, "Loan retrieved successfully", loan);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<LoanResponseDto>>> listLoans(
            @RequestParam(name = "memberId", required = false) Long memberId) {
        List<LoanResponseDto> list = (memberId == null)
                ? loanService.listAll()
                : loanService.listByMember(memberId);
        ApiResponse<List<LoanResponseDto>> response = new ApiResponse<>(true, "Loans listed successfully", list);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLoan(@PathVariable Long id) {
        loanService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Loan deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
