package com.alkemy.ong.services;

import com.alkemy.ong.models.OrganizationModel;
import com.alkemy.ong.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository orgRepo;

    public List<OrganizationModel> findAll(){
        return orgRepo.findAll();
    }
}
