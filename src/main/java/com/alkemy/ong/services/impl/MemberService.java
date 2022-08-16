package com.alkemy.ong.services.impl;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.MemberMapper;
import com.alkemy.ong.models.Member;
import com.alkemy.ong.models.News;
import com.alkemy.ong.repositories.MemberRepository;
import com.alkemy.ong.services.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberResponse createMember(MemberRequest memberRequest) {
        Member member = memberMapper.memberRequestToMember(memberRequest);
        return memberMapper.memberToMemberResponse(memberRepository.save(member));
    }

    @Override
    public MemberResponse updateMember(UUID id, MemberRequest memberRequest) {
        memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Members", "id", id));
        Member member = memberMapper.memberRequestToMember(memberRequest);
        member.setId(id);
        return memberMapper.memberToMemberResponse(memberRepository.save(member));
    }
}
