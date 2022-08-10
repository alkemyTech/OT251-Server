package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.contact.ContactRequest;
import com.alkemy.ong.dto.response.contact.ContactResponse;
import com.alkemy.ong.models.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    public Contact contactRequestToEntity (ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setEmail(contactRequest.getEmail());
        contact.setName(contactRequest.getName());
        contact.setPhone(contactRequest.getPhone());
        contact.setMessage(contactRequest.getMessage());
        return contact;
    }

    public ContactResponse mapContactResponse(Contact contact){
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setId(contact.getId());
        contactResponse.setName(contact.getName());
        contactResponse.setPhone(contact.getPhone());
        contactResponse.setEmail(contact.getEmail());
        contactResponse.setMessage(contact.getMessage());
        return contactResponse;
    }
}
