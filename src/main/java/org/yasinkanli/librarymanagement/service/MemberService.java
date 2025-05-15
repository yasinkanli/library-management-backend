package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.MemberRequestDto;
import org.yasinkanli.librarymanagement.dto.MemberResponseDto;

import java.util.List;

public interface MemberService {
    MemberResponseDto create(MemberRequestDto dto);
    MemberResponseDto getById(Long id);
    List<MemberResponseDto> listAll();
    List<MemberResponseDto> searchByName(String name);
    MemberResponseDto update(Long id, MemberRequestDto dto);
    void delete(Long id);
}