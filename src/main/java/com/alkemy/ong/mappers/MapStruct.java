package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.response.contact.OrganizationDTO;
import com.alkemy.ong.models.OrganizationModel;

public class MapStruct {
    public OrganizationDTO organizationToOrganizationDTO(OrganizationModel organization){
        if (organization==null)
            return null;
        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setImage(organization.getImage());
        organizationDTO.setPhone(organization.getPhone());
        organizationDTO.setAddress(organization.getAddress());

        return organizationDTO;
    }
}
