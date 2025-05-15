package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.ReviewRequestDto;
import org.yasinkanli.librarymanagement.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto create(ReviewRequestDto dto);
    ReviewResponseDto getById(Long id);
    List<ReviewResponseDto> listAll();
    List<ReviewResponseDto> listByBook(Long bookId);
    List<ReviewResponseDto> listByMember(Long memberId);
    void delete(Long id);
}