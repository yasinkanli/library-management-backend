package org.yasinkanli.librarymanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanResponseDto {
    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public LocalDateTime getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}