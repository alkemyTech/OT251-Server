package com.alkemy.ong.services.impl;

import com.alkemy.ong.dto.request.contact.ContactRequest;
import com.alkemy.ong.dto.response.contact.ContactResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.ContactMapper;
import com.alkemy.ong.models.Contact;
import com.alkemy.ong.repositories.ContactRepository;
import com.alkemy.ong.services.IContactService;
import com.alkemy.ong.services.IMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private IMailSenderService mailSenderService;

    @Override
    public ContactResponse save(ContactRequest contactRequest) {
        Contact contact = contactMapper.contactRequestToEntity(contactRequest);
        mailSenderService.sendMailContact(contactRequest.getEmail());
        return contactMapper.mapContactResponse(contactRepo.save(contact));
    }

    @Override
    public List<ContactResponse> getAll(){
        List<Contact> contacts = contactRepo.findAll();
        if(contacts.isEmpty()) {
            throw new ResourceNotFoundException("List contacts");
        }
        return contacts.stream().map(contact -> contactMapper.mapContactResponse(contact)).collect(Collectors.toList());
    }
}
