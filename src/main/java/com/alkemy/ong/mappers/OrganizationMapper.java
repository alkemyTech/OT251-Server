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
        organizationDTO.setInstagramUrl(organization.getInstagramUrl());
        organizationDTO.setLinkedinUrl(organization.getLinkedinUrl());

        return organizationDTO;
    }

    public Organization organizationRequestToEntity(OrganizationRequest organizationRequest){
        if(organizationRequest == null) return null;

        Organization organization = new Organization();
        organization.setId(organizationRequest.getId());
        organization.setAddress(organizationRequest.getAddress());
        organization.setName(organizationRequest.getName());
        organization.setImage(organizationRequest.getImage());
        organization.setPhone(organizationRequest.getPhone());
        organization.setFacebookUrl(organizationRequest.getFacebookUrl());
        organization.setInstagramUrl(organizationRequest.getInstagramUrl());
        organization.setLinkedinUrl(organizationRequest.getLinkedinUrl());

        return organization;
    }
}
