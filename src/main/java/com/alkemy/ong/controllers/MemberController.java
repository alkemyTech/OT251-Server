package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.member.MemberRequest;
import com.alkemy.ong.dto.response.member.MemberResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.services.IMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/members")
@Tag(name = "Members", description = "Members controller")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @Operation(summary = "Method to get all Members", description = "Return all Members")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Return all Members"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - No Members Found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<PageResultResponse<MemberResponse>> getMemberList(@RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getMemberList(page));
    }

    @Operation(summary = "Method to create a Member", description = "Save a new Member item in the database and return it")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Member create successfully"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Param invalid")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Valid MemberRequest memberRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.createMember(memberRequest));
    }

    @Operation(summary = "Method to update a Member by ID", description = "Update a Member if the ID exist")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Member update successfully"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Param invalid"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - A Member with that ID is Not Found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable UUID id, @RequestBody @Valid MemberRequest memberRequest){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.updateMember(id, memberRequest));
    }

    @Operation(summary = "Method to delete a Member by ID", description = "Delete a Member if the ID exist")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT - Member delete successfully"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - A Member with that ID is Not Found")})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable UUID id){
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
