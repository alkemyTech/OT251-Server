package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.organization.OrganizationRequest;
import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import com.alkemy.ong.models.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public OrganizationResponse organizationToOrganizationDTO(Organization organization){
        if (organization==null)
            return null;
        OrganizationResponse organizationDTO = new OrganizationResponse();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setImage(organization.getImage());
        organizationDTO.setPhone(organization.getPhone());
        organizationDTO.setAddress(organization.getAddress());
        organizationDTO.setFacebookUrl(organization.getFacebookUrl());
        organizationDTO.setLinkedinUrl(organization.getLinkedinUrl());
        organizationDTO.setInstagramUrl(organization.getInstagramUrl());

        return organizationDTO;
    }

    public Organization organizationRequestToEntity(OrganizationRequest organizationDTO){
        if(organizationDTO == null) return null;

        Organization organization = new Organization();
        organization.setId(organizationDTO.getId());
        organization.setName(organizationDTO.getName());
        organization.setImage(organizationDTO.getImage());
        organization.setPhone(organizationDTO.getPhone());
        organization.setAddress(organizationDTO.getAddress());
        organization.setFacebookUrl(organizationDTO.getFacebookUrl());
        organization.setLinkedinUrl(organizationDTO.getLinkedinUrl());
        organization.setInstagramUrl(organizationDTO.getInstagramUrl());
        return organization;
    }
}
