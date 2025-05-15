package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.MemberRequestDto;
import org.yasinkanli.librarymanagement.dto.MemberResponseDto;
import org.yasinkanli.librarymanagement.service.MemberService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<MemberResponseDto>> createMember(@Valid @RequestBody MemberRequestDto dto) {
        MemberResponseDto created = memberService.create(dto);
        ApiResponse<MemberResponseDto> response = new ApiResponse<>(true, "Member created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponseDto>> getMemberById(@PathVariable Long id) {
        MemberResponseDto member = memberService.getById(id);
        ApiResponse<MemberResponseDto> response = new ApiResponse<>(true, "Member retrieved successfully", member);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MemberResponseDto>>> listMembers(@RequestParam(name = "name", required = false) String name) {
        List<MemberResponseDto> members = (name == null)
                ? memberService.listAll()
                : memberService.searchByName(name);
        ApiResponse<List<MemberResponseDto>> response = new ApiResponse<>(true, "Members listed successfully", members);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponseDto>> updateMember(@PathVariable Long id, @Valid @RequestBody MemberRequestDto dto) {
        MemberResponseDto updated = memberService.update(id, dto);
        ApiResponse<MemberResponseDto> response = new ApiResponse<>(true, "Member updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Member deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
