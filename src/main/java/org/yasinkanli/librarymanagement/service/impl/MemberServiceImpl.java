package org.yasinkanli.librarymanagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yasinkanli.librarymanagement.dto.MemberRequestDto;
import org.yasinkanli.librarymanagement.dto.MemberResponseDto;
import org.yasinkanli.librarymanagement.entity.Member;
import org.yasinkanli.librarymanagement.entity.MembershipCard;
import org.yasinkanli.librarymanagement.mapper.GenericMapper;
import org.yasinkanli.librarymanagement.repository.MemberRepository;
import org.yasinkanli.librarymanagement.service.MemberService;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GenericMapper mapper;

    @Override
    public MemberResponseDto create(MemberRequestDto dto) {
        Member member = new Member();
        member.setName(dto.getName());
        MembershipCard card = new MembershipCard();
        card.setCardNumber(dto.getCardNumber());
        member.setCard(card);
        Member saved = memberRepository.save(member);
        return mapper.map(saved, MemberResponseDto.class);
    }

    @Override
    public MemberResponseDto getById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
        return mapper.map(member, MemberResponseDto.class);
    }

    @Override
    public List<MemberResponseDto> listAll() {
        return mapper.mapList(memberRepository.findAll(), MemberResponseDto.class);
    }

    @Override
    public List<MemberResponseDto> searchByName(String name) {
        List<Member> found = memberRepository.findByNameContainingIgnoreCase(name);
        return mapper.mapList(found, MemberResponseDto.class);
    }

    @Override
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
        existing.setName(dto.getName());
        existing.getCard().setCardNumber(dto.getCardNumber());
        Member updated = memberRepository.save(existing);
        return mapper.map(updated, MemberResponseDto.class);
    }

    @Override
    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new EntityNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
