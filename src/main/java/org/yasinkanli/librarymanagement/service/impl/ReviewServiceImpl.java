package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.ReviewRequestDto;
import org.yasinkanli.librarymanagement.dto.ReviewResponseDto;
import org.yasinkanli.librarymanagement.entity.Book;
import org.yasinkanli.librarymanagement.entity.Member;
import org.yasinkanli.librarymanagement.entity.Review;
import org.yasinkanli.librarymanagement.mapper.GenericMapper;
import org.yasinkanli.librarymanagement.repository.BookRepository;
import org.yasinkanli.librarymanagement.repository.MemberRepository;
import org.yasinkanli.librarymanagement.repository.ReviewRepository;
import org.yasinkanli.librarymanagement.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GenericMapper mapper;

    @Override
    public ReviewResponseDto create(ReviewRequestDto dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + dto.getBookId()));
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found: " + dto.getMemberId()));

        Review review = new Review();
        review.setBook(book);
        review.setMember(member);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        Review saved = reviewRepository.save(review);
        return mapper.map(saved, ReviewResponseDto.class);
    }

    @Override
    public ReviewResponseDto getById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found: " + id));
        return mapper.map(review, ReviewResponseDto.class);
    }

    @Override
    public List<ReviewResponseDto> listAll() {
        return mapper.mapList(reviewRepository.findAll(), ReviewResponseDto.class);
    }

    @Override
    public List<ReviewResponseDto> listByBook(Long bookId) {
        List<Review> reviews = reviewRepository.findByBookId(bookId);
        return mapper.mapList(reviews, ReviewResponseDto.class);
    }

    @Override
    public List<ReviewResponseDto> listByMember(Long memberId) {
        List<Review> reviews = reviewRepository.findByMemberId(memberId);
        return mapper.mapList(reviews, ReviewResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review not found: " + id);
        }
        reviewRepository.deleteById(id);
    }
}