package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.ReviewRequestDto;
import org.yasinkanli.librarymanagement.dto.ReviewResponseDto;
import org.yasinkanli.librarymanagement.service.ReviewService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ReviewResponseDto>> createReview(@Valid @RequestBody ReviewRequestDto dto) {
        ReviewResponseDto created = reviewService.create(dto);
        ApiResponse<ReviewResponseDto> response = new ApiResponse<>(true, "Review created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReviewResponseDto>> getReviewById(@PathVariable Long id) {
        ReviewResponseDto review = reviewService.getById(id);
        ApiResponse<ReviewResponseDto> response = new ApiResponse<>(true, "Review retrieved successfully", review);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ReviewResponseDto>>> listReviews(
            @RequestParam(name = "bookId", required = false) Long bookId,
            @RequestParam(name = "memberId", required = false) Long memberId) {

        List<ReviewResponseDto> reviews;
        if (bookId != null) {
            reviews = reviewService.listByBook(bookId);
        } else if (memberId != null) {
            reviews = reviewService.listByMember(memberId);
        } else {
            reviews = reviewService.listAll();
        }

        ApiResponse<List<ReviewResponseDto>> response = new ApiResponse<>(true, "Reviews listed successfully", reviews);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long id) {
        reviewService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Review deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
