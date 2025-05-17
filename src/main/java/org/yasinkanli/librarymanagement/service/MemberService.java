package org.yasinkanli.librarymanagement.service;

import org.yasinkanli.librarymanagement.dto.MemberDto;
import org.yasinkanli.librarymanagement.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto create(MemberDto dto);
    MemberDto getById(Long id);
    List<MemberDto> listAll();
    List<MemberDto> searchByName(String name);
    MemberDto update(Long id, MemberDto dto);
    void delete(Long id);
}