package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.BookDto;
import org.yasinkanli.librarymanagement.service.BookService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BookDto>> createBook(@Valid @RequestBody BookDto dto) {
        BookDto created = bookService.create(dto);
        ApiResponse<BookDto> response = new ApiResponse<>(true, "Book created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDto>> getBookById(@PathVariable Long id) {
        BookDto book = bookService.getById(id);
        ApiResponse<BookDto> response = new ApiResponse<>(true, "Book retrieved successfully", book);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<BookDto>>> listAllBooks(@RequestParam(name = "title", required = false) String title) {
        List<BookDto> books = (title == null)
                ? bookService.listAll()
                : bookService.searchByTitle(title);
        ApiResponse<List<BookDto>> response = new ApiResponse<>(true, "Books listed successfully", books);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDto>> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        BookDto updated = bookService.update(id, bookDto);
        ApiResponse<BookDto> response = new ApiResponse<>(true, "Book updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Book deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}