package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;

import java.util.UUID;

public interface IMemberService {

    MemberResponse createMember(MemberRequest memberRequest);

    MemberResponse updateMember(UUID id, MemberRequest memberRequest);
}
