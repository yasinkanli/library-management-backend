package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.BookDto;
import org.yasinkanli.librarymanagement.entity.Author;
import org.yasinkanli.librarymanagement.entity.Book;
import org.yasinkanli.librarymanagement.mapper.GenericMapper;
import org.yasinkanli.librarymanagement.repository.AuthorRepository;
import org.yasinkanli.librarymanagement.repository.BookRepository;
import org.yasinkanli.librarymanagement.service.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenericMapper mapper;


    @Override
    public BookDto create(BookDto dto) {
        Book bookEntity = mapper.map(dto, Book.class);

        Set<Author> authors = new HashSet<>(authorRepository.findAllById(dto.getAuthorIds()));

        if (authors.size() != dto.getAuthorIds().size()) {
            throw new IllegalArgumentException("Some author IDs are invalid.");
        }

        bookEntity.setAuthors(authors);

        Book saved = bookRepository.save(bookEntity);

        BookDto resultDto = mapper.map(saved, BookDto.class);
        if (resultDto.getAuthors() != null) {
            resultDto.getAuthors().forEach(a -> a.setBooks(null));
        }

        return resultDto;
    }


    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return mapper.map(book, BookDto.class);
    }

    @Override
    public List<BookDto> listAll() {
        List<BookDto> list = mapper.mapList(bookRepository.findAll(), BookDto.class);
        list.forEach(book -> {
            if (book.getAuthors() != null) {
                book.getAuthors().forEach(author -> author.setBooks(null));
            }
        });
        return list;
    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        existing.setTitle(dto.getTitle());

        Book updated = bookRepository.save(existing);
        return mapper.map(updated, BookDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> searchByTitle(String title) {
        List<Book> found = bookRepository.findByTitleContainingIgnoreCase(title);
        return mapper.mapList(found, BookDto.class);
    }

}
