package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.AuthorDto;
import org.yasinkanli.librarymanagement.entity.Author;
import org.yasinkanli.librarymanagement.mapper.GenericMapper;
import org.yasinkanli.librarymanagement.repository.AuthorRepository;
import org.yasinkanli.librarymanagement.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenericMapper mapper;

    @Override
    public AuthorDto create(AuthorDto dto) {
        Author entity2 = mapper.map(dto, Author.class);
        Author saved = authorRepository.save(entity2);
        return mapper.map(saved, AuthorDto.class);
    }

    @Override
    public AuthorDto getById(Long id) {
        Author entity = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        return mapper.map(entity, AuthorDto.class);
    }

    @Override
    public List<AuthorDto> listAll() {
        List<AuthorDto> list = mapper.mapList(authorRepository.findAll(), AuthorDto.class);
        list.forEach(author -> {
            if (author.getBooks() != null) {
                author.getBooks().forEach(book -> book.setAuthors(null));
            }
        });
        return list;
    }

    @Override
    public AuthorDto update(Long id, AuthorDto dto) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        existing.setName(dto.getName());
        Author updated = authorRepository.save(existing);
        return mapper.map(updated, AuthorDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
