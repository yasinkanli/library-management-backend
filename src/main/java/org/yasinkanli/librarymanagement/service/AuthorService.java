package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.AuthorDto;
import org.yasinkanli.librarymanagement.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto create(AuthorDto dto);
    AuthorDto getById(Long id);
    List<AuthorDto> listAll();
    AuthorDto update(Long id, AuthorDto dto);
    void delete(Long id);
}