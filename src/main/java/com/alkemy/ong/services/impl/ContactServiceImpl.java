package com.alkemy.ong.services.impl;

import com.alkemy.ong.models.Contact;
import com.alkemy.ong.repositories.ContactRepository;
import com.alkemy.ong.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactRepository contactRepo;

    @Override
    public void save(Contact contact) {
        contactRepo.save(contact);
    }
}
