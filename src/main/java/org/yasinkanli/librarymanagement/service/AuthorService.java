package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.AuthorRequestDto;
import org.yasinkanli.librarymanagement.dto.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto create(AuthorRequestDto dto);
    AuthorResponseDto getById(Long id);
    List<AuthorResponseDto> listAll();
    AuthorResponseDto update(Long id, AuthorRequestDto dto);
    void delete(Long id);
}