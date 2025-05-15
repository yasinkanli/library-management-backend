package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.BookRequestDto;
import org.yasinkanli.librarymanagement.dto.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto create(BookRequestDto dto);
    BookResponseDto getById(Long id);
    List<BookResponseDto> listAll();
    void delete(Long id);
    BookResponseDto update(Long id,BookRequestDto dto);
    List<BookResponseDto> searchByTitle(String title);
}