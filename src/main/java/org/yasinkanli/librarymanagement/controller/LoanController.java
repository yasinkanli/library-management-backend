package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.LoanDto;
import org.yasinkanli.librarymanagement.service.LoanService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<LoanDto>> createLoan(@Valid @RequestBody LoanDto dto) {
        LoanDto created = loanService.createLoan(dto);
        ApiResponse<LoanDto> response = new ApiResponse<>(true, "Loan created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LoanDto>> getLoanById(@PathVariable Long id) {
        LoanDto loan = loanService.getById(id);
        ApiResponse<LoanDto> response = new ApiResponse<>(true, "Loan retrieved successfully", loan);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<LoanDto>>> listLoans(
            @RequestParam(name = "memberId", required = false) Long memberId) {
        List<LoanDto> list = (memberId == null)
                ? loanService.listAll()
                : loanService.listByMember(memberId);
        ApiResponse<List<LoanDto>> response = new ApiResponse<>(true, "Loans listed successfully", list);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLoan(@PathVariable Long id) {
        loanService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Loan deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
