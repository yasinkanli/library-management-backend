package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.LoanRequestDto;
import org.yasinkanli.librarymanagement.dto.LoanResponseDto;
import org.yasinkanli.librarymanagement.entity.Book;
import org.yasinkanli.librarymanagement.entity.Loan;
import org.yasinkanli.librarymanagement.entity.Member;
import org.yasinkanli.librarymanagement.exception.BusinessException;
import org.yasinkanli.librarymanagement.mapper.GenericMapper;
import org.yasinkanli.librarymanagement.repository.BookRepository;
import org.yasinkanli.librarymanagement.repository.LoanRepository;
import org.yasinkanli.librarymanagement.repository.MemberRepository;
import org.yasinkanli.librarymanagement.service.LoanService;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenericMapper mapper;

    @Value("${library.max-loans-per-member}")
    private int maxLoansPerMember;

    @Override
    public LoanResponseDto createLoan(LoanRequestDto dto) {
        long existing = loanRepository.countByMemberId(dto.getMemberId());
        if (existing >= maxLoansPerMember) {
            throw new BusinessException(
                    "Member has already reached max allowed loans: " + maxLoansPerMember);
        }

        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + dto.getMemberId()));
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + dto.getBookId()));

        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(maxLoansPerMember));

        Loan saved = loanRepository.save(loan);
        return mapper.map(saved, LoanResponseDto.class);
    }

    @Override
    public LoanResponseDto getById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id));
        return mapper.map(loan, LoanResponseDto.class);
    }

    @Override
    public List<LoanResponseDto> listAll() {
        return mapper.mapList(loanRepository.findAll(), LoanResponseDto.class);
    }

    @Override
    public List<LoanResponseDto> listByMember(Long memberId) {
        List<Loan> loans = loanRepository.findByMemberId(memberId);
        return mapper.mapList(loans, LoanResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new EntityNotFoundException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }
}