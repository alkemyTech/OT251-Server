package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.response.contact.OrganizationDTO;
import com.alkemy.ong.mappers.MapStruct;
import com.alkemy.ong.models.OrganizationModel;
import com.alkemy.ong.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService orgServ;

    @Autowired
    MapStruct mapStruct;

    @GetMapping("/public")
    public OrganizationDTO infoOrganization(){
        Optional<OrganizationModel> organization = orgServ.findAll().stream().findFirst();
        if(!organization.isPresent()) return null;
        return mapStruct.organizationToOrganizationDTO(organization.get());
    }

}
