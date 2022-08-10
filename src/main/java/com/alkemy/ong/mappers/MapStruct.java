package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.models.Organization;
import org.springframework.stereotype.Component;

@Component
public class MapStruct {
    public OrganizationResponse organizationToOrganizationDTO(Organization organization){
        if (organization==null)
            return null;
        OrganizationResponse organizationDTO = new OrganizationResponse();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setImage(organization.getImage());
        organizationDTO.setPhone(organization.getPhone());
        organizationDTO.setAddress(organization.getAddress());

        return organizationDTO;
    }
}
