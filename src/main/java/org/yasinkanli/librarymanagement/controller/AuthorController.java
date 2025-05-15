package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.AuthorRequestDto;
import org.yasinkanli.librarymanagement.dto.AuthorResponseDto;
import org.yasinkanli.librarymanagement.service.AuthorService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> createAuthor(@Valid @RequestBody AuthorRequestDto dto) {
        AuthorResponseDto created = authorService.create(dto);
        ApiResponse<AuthorResponseDto> response = new ApiResponse<>(true, "Author created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> getAuthorById(@PathVariable Long id) {
        AuthorResponseDto author = authorService.getById(id);
        ApiResponse<AuthorResponseDto> response = new ApiResponse<>(true, "Author retrieved successfully", author);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<AuthorResponseDto>>> listAuthors() {
        List<AuthorResponseDto> authors = authorService.listAll();
        ApiResponse<List<AuthorResponseDto>> response = new ApiResponse<>(true, "Authors listed successfully", authors);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorResponseDto>> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDto dto) {
        AuthorResponseDto updated = authorService.update(id, dto);
        ApiResponse<AuthorResponseDto> response = new ApiResponse<>(true, "Author updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Author deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
