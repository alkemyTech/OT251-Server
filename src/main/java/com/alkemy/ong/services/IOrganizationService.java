package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.organization.OrganizationRequest;
import com.alkemy.ong.dto.response.organization.OrganizationResponse;
import com.alkemy.ong.models.OrganizationModel;
import java.util.List;
import java.util.UUID;

public interface IOrganizationService {
    
    public List<OrganizationModel> findAll();
    
    public OrganizationResponse update (UUID id, OrganizationRequest organizationDTO);
}
