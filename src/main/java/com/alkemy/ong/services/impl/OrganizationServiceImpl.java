package com.alkemy.ong.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.request.organization.OrganizationRequest;
import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.MapStruct;
import com.alkemy.ong.models.Organization;
import com.alkemy.ong.repositories.OrganizationRepository;
import com.alkemy.ong.services.IOrganizationService;

@Service
public class OrganizationServiceImpl implements IOrganizationService{
    
    @Autowired
    private OrganizationRepository orgRepo;
    
    @Autowired
    private MapStruct mapStruct;

    @Override
    public List<Organization> findAll() {
        return orgRepo.findAll();
    }

    @Override
    public OrganizationResponse update(UUID id, OrganizationRequest organizationDTO) {
        Organization organization = orgRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrganizationModel", "id", id));
        organization.setName(organizationDTO.getName());
        organization.setImage(organizationDTO.getImage());
        organization.setPhone(organizationDTO.getPhone());
        organization.setAddress(organizationDTO.getAddress());
        return mapStruct.organizationToOrganizationDTO(orgRepo.save(organization));
    }
    
}
