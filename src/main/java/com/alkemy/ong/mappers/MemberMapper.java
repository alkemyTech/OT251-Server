package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;
import com.alkemy.ong.models.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member memberRequestToMember(MemberRequest memberRequest){
        if(memberRequest==null)
            return null;
        Member member = new Member();
        member.setName(memberRequest.getName());
        member.setImage(memberRequest.getImage());
        member.setDescription(memberRequest.getDescription());
        member.setFacebookUrl(memberRequest.getFacebookUrl());
        member.setInstagramUrl(memberRequest.getInstagramUrl());
        member.setLinkedinUrl(memberRequest.getLinkedinUrl());
        return member;
    }

    public MemberResponse memberToMemberResponse(Member member){
        if(member==null)
            return null;
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.setId(member.getId());
        memberResponse.setName(member.getName());
        memberResponse.setImage(member.getImage());
        memberResponse.setDescription(member.getDescription());
        memberResponse.setFacebookUrl(member.getFacebookUrl());
        memberResponse.setInstagramUrl(member.getInstagramUrl());
        memberResponse.setLinkedinUrl(member.getLinkedinUrl());
        return memberResponse;
    }
}
