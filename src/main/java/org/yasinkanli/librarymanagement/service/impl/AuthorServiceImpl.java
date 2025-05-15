package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.AuthorRequestDto;
import org.yasinkanli.librarymanagement.dto.AuthorResponseDto;
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
    public AuthorResponseDto create(AuthorRequestDto dto) {
        Author entity2 = mapper.map(dto, Author.class);
        Author saved = authorRepository.save(entity2);
        return mapper.map(saved, AuthorResponseDto.class);
    }

    @Override
    public AuthorResponseDto getById(Long id) {
        Author entity = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        return mapper.map(entity, AuthorResponseDto.class);
    }

    @Override
    public List<AuthorResponseDto> listAll() {
        return mapper.mapList(authorRepository.findAll(), AuthorResponseDto.class);
    }

    @Override
    public AuthorResponseDto update(Long id, AuthorRequestDto dto) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + id));
        existing.setName(dto.getName());
        Author updated = authorRepository.save(existing);
        return mapper.map(updated, AuthorResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
