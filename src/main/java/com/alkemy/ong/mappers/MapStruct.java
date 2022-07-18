package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.response.contact.OrganizationDTO;
import com.alkemy.ong.models.organizacion_models;

public class MapStruct {
    public OrganizationDTO organizationToOrganizationDTO(organizacion_models organization){
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
