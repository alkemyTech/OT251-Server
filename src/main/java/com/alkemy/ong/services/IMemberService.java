package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;

import java.util.List;
import java.util.UUID;

public interface IMemberService {

    MemberResponse createMember(MemberRequest memberRequest);

    MemberResponse updateMember(UUID id, MemberRequest memberRequest);

    List<MemberResponse> findAll();

    void delete(UUID id);

    public PageResultResponse<MemberResponse> getMemberList(Integer pageNumber);
}
