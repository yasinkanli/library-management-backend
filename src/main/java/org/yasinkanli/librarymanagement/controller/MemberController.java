package org.yasinkanli.librarymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yasinkanli.librarymanagement.dto.MemberDto;
import org.yasinkanli.librarymanagement.service.MemberService;
import org.yasinkanli.librarymanagement.web.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<MemberDto>> createMember(@Valid @RequestBody MemberDto dto) {
        MemberDto created = memberService.create(dto);
        ApiResponse<MemberDto> response = new ApiResponse<>(true, "Member created successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberDto>> getMemberById(@PathVariable Long id) {
        MemberDto member = memberService.getById(id);
        ApiResponse<MemberDto> response = new ApiResponse<>(true, "Member retrieved successfully", member);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MemberDto>>> listMembers(@RequestParam(name = "name", required = false) String name) {
        List<MemberDto> members = (name == null)
                ? memberService.listAll()
                : memberService.searchByName(name);
        ApiResponse<List<MemberDto>> response = new ApiResponse<>(true, "Members listed successfully", members);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberDto>> updateMember(@PathVariable Long id, @Valid @RequestBody MemberDto dto) {
        MemberDto updated = memberService.update(id, dto);
        ApiResponse<MemberDto> response = new ApiResponse<>(true, "Member updated successfully", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Member deleted successfully", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
