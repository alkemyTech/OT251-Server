package com.alkemy.ong.services.impl;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.MemberMapper;
import com.alkemy.ong.mappers.PageResultMapper;
import com.alkemy.ong.models.Member;
import com.alkemy.ong.repositories.MemberRepository;
import com.alkemy.ong.services.IMemberService;
import com.alkemy.ong.utils.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.alkemy.ong.utils.ApiConstants.PATH_MEMBERS;

@Service
public class MemberServiceImpl extends ClassUtils<Member, UUID, MemberRepository>  implements IMemberService {

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

    @Override
    public List<MemberResponse> findAll() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) {
            throw new ResourceNotFoundException("List Member");
        }
        return members.stream().map(member -> memberMapper.memberToMemberResponse(member)).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Members", "id", id));
        memberRepository.delete(member);
    }

    @Override
    public PageResultResponse<MemberResponse> getMemberList(Integer pageNumber) {
        Page<Member> page = getPage(pageNumber);
        if(!page.hasContent()){
            throw new ResourceNotFoundException();
        }
        List<MemberResponse> memberResponses = memberMapper.entities2ListResponse(page.getContent());
        String previous = getPrevious(PATH_MEMBERS, pageNumber);
        String next = getNext(page, PATH_MEMBERS, pageNumber);

        PageResultMapper<MemberResponse> response = new PageResultMapper<>();
        return response.mapPage(memberResponses, previous, next);
    }
}
