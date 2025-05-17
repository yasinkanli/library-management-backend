package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto create(BookDto dto);
    BookDto getById(Long id);
    List<BookDto> listAll();
    void delete(Long id);
    BookDto update(Long id,BookDto dto);
    List<BookDto> searchByTitle(String title);
}