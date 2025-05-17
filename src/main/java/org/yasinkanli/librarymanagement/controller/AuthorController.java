package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.AuthorDto;
import org.yasinkanli.librarymanagement.service.AuthorService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AuthorDto>> createAuthor(@Valid @RequestBody AuthorDto dto) {
        AuthorDto created = authorService.create(dto);
        ApiResponse<AuthorDto> response = new ApiResponse<>(true, "Author created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDto>> getAuthorById(@PathVariable Long id) {
        AuthorDto author = authorService.getById(id);
        ApiResponse<AuthorDto> response = new ApiResponse<>(true, "Author retrieved successfully", author);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<AuthorDto>>> listAuthors() {
        List<AuthorDto> authors = authorService.listAll();
        ApiResponse<List<AuthorDto>> response = new ApiResponse<>(true, "Authors listed successfully", authors);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDto>> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDto dto) {
        AuthorDto updated = authorService.update(id, dto);
        ApiResponse<AuthorDto> response = new ApiResponse<>(true, "Author updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Author deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
