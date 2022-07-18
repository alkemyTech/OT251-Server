package com.alkemy.ong.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository orgRepo;

    public List<organizacion_models> findAll(){
        return orgRepo.findAll();
    }
}
