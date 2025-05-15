package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.BookRequestDto;
import org.yasinkanli.librarymanagement.dto.BookResponseDto;
import org.yasinkanli.librarymanagement.service.BookService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BookResponseDto>> createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto created = bookService.create(bookRequestDto);
        ApiResponse<BookResponseDto> response = new ApiResponse<>(true, "Book created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> getBookById(@PathVariable Long id) {
        BookResponseDto book = bookService.getById(id);
        ApiResponse<BookResponseDto> response = new ApiResponse<>(true, "Book retrieved successfully", book);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> listAllBooks(@RequestParam(name = "title", required = false) String title) {
        List<BookResponseDto> books = (title == null)
                ? bookService.listAll()
                : bookService.searchByTitle(title);
        ApiResponse<List<BookResponseDto>> response = new ApiResponse<>(true, "Books listed successfully", books);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto updated = bookService.update(id, bookRequestDto);
        ApiResponse<BookResponseDto> response = new ApiResponse<>(true, "Book updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Book deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}