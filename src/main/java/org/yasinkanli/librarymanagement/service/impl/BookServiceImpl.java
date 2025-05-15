package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.BookRequestDto;
import org.yasinkanli.librarymanagement.dto.BookResponseDto;
import org.yasinkanli.librarymanagement.entity.Book;           // <-- doğru import
import org.yasinkanli.librarymanagement.mapper.GenericMapper;
import org.yasinkanli.librarymanagement.repository.BookRepository;
import org.yasinkanli.librarymanagement.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenericMapper mapper;


    @Override
    public BookResponseDto create(BookRequestDto dto) {
        Book bookEntity = mapper.map(dto, Book.class);
        Book saved = bookRepository.save(bookEntity);
        return mapper.map(saved, BookResponseDto.class);
    }

    @Override
    public BookResponseDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return mapper.map(book, BookResponseDto.class);
    }

    @Override
    public List<BookResponseDto> listAll() {
        return mapper.mapList(bookRepository.findAll(), BookResponseDto.class);
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        existing.setTitle(dto.getTitle());

        Book updated = bookRepository.save(existing);
        return mapper.map(updated, BookResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDto> searchByTitle(String title) {
        List<Book> found = bookRepository.findByTitleContainingIgnoreCase(title);
        return mapper.mapList(found, BookResponseDto.class);
    }

}
