package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;
import com.alkemy.ong.services.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    IMemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMembers(){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findAll());
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid MemberRequest memberRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable UUID id, @RequestBody @Valid MemberRequest memberRequest){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMember(id, memberRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable UUID id){
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
