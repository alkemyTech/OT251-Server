package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.services.IMemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    IMemberService memberService;

    @ApiOperation(value = "Method to get all Members", notes = "Return all Members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Return all Members"),
            @ApiResponse(code = 404, message = "NOT FOUND - No Members Found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<PageResultResponse<MemberResponse>> getMemberList(@RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemberList(page));
    }

    @ApiOperation(value = "Method to create a Member", notes = "Save a new Member item in the database and return it")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - Member create successfully"),
            @ApiResponse(code = 400, message = "BAD REQUEST - Param invalid")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid MemberRequest memberRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberRequest));
    }

    @ApiOperation(value = "Method to update a Member by ID", notes = "Update a Member if the ID exist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Member update successfully"),
            @ApiResponse(code = 400, message = "BAD REQUEST - Param invalid"),
            @ApiResponse(code = 404, message = "NOT FOUND - A Member with that ID is Not Found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable UUID id, @RequestBody @Valid MemberRequest memberRequest){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMember(id, memberRequest));
    }

    @ApiOperation(value = "Method to delete a Member by ID", notes = "Delete a Member if the ID exist")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO CONTENT - Member delete successfully"),
            @ApiResponse(code = 404, message = "NOT FOUND - A Member with that ID is Not Found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable UUID id){
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
