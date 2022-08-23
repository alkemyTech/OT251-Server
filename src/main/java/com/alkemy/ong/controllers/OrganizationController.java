package com.alkemy.ong.controllers;


import javax.validation.Valid;

import com.alkemy.ong.services.ISlideService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.request.organization.OrganizationRequest;
import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import com.alkemy.ong.mappers.OrganizationMapper;
import com.alkemy.ong.services.IOrganizationService;

@RestController
@RequestMapping("/organization")
@Tag(name = "Organizations", description = "Organizations controller")
public class OrganizationController {

    @Autowired
    private IOrganizationService orgServ;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private ISlideService slideService;

    @GetMapping("/public")
    public ResponseEntity<OrganizationResponse> infoOrganization() {
        return ResponseEntity.status(HttpStatus.OK).body(orgServ.getPublicInfo());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/public")
    public ResponseEntity<OrganizationResponse> updateOrganization(@RequestBody @Valid OrganizationRequest organization){
        return ResponseEntity.status(HttpStatus.OK).body(orgServ.update(organization.getId(), organization));
    }

}
