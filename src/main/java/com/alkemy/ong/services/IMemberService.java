package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;

public interface IMemberService {

    MemberResponse createMember(MemberRequest memberRequest);
}
