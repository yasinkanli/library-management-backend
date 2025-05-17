package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.LoanDto;
import org.yasinkanli.librarymanagement.dto.LoanDto;
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
    public LoanDto createLoan(LoanDto dto) {
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
        LoanDto result = mapper.map(saved, LoanDto.class);
        if (result.getBook() != null && result.getBook().getAuthors() != null) {
            result.getBook().getAuthors().forEach(author -> author.setBooks(null));
        }
        return result;
    }

    @Override
    public LoanDto getById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id));
        return mapper.map(loan, LoanDto.class);
    }

    @Override
    public List<LoanDto> listAll() {
        List<LoanDto> list = mapper.mapList(loanRepository.findAll(), LoanDto.class);
        list.forEach(loan -> {
            if (loan.getBook() != null && loan.getBook().getAuthors() != null) {
                loan.getBook().getAuthors().forEach(author -> author.setBooks(null));
            }
        });
        return list;
    }

    @Override
    public List<LoanDto> listByMember(Long memberId) {
        List<Loan> loans = loanRepository.findByMemberId(memberId);
        return mapper.mapList(loans, LoanDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new EntityNotFoundException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }
}