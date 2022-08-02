package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.organization.OrganizationRequest;
import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.MapStruct;
import com.alkemy.ong.models.OrganizationModel;
import com.alkemy.ong.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository orgRepo;
    
    @Autowired
    private MapStruct mapStruct;

    public List<OrganizationModel> findAll(){
        return orgRepo.findAll();
    }
    
    public OrganizationResponse update (UUID id, OrganizationRequest organizationDTO){
        OrganizationModel organization = orgRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrganizationModel", "id", id));
        organization.setName(organizationDTO.getName());
        organization.setImage(organizationDTO.getImage());
        organization.setPhone(organizationDTO.getPhone());
        organization.setAddress(organizationDTO.getAddress());
        return mapStruct.organizationToOrganizationDTO(orgRepo.save(organization));
    }
}
