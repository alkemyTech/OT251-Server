package com.alkemy.ong.controllers;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.request.organization.OrganizationRequest;
import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import com.alkemy.ong.mappers.MapStruct;
import com.alkemy.ong.models.Organization;
import com.alkemy.ong.services.IOrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    IOrganizationService orgServ;

    @Autowired
    MapStruct mapStruct;

    @GetMapping("/public")
    public OrganizationResponse infoOrganization(){
        Optional<Organization> organization = orgServ.findAll().stream().findFirst();
        if(!organization.isPresent()) return null;
        return mapStruct.organizationToOrganizationDTO(organization.get());
    }
    
    @PostMapping("/public")
    public ResponseEntity<OrganizationResponse> updateOrganization(@RequestBody @Valid OrganizationRequest organization){
        return ResponseEntity.status(HttpStatus.OK).body(orgServ.update(organization.getId(), organization));
    }

}
