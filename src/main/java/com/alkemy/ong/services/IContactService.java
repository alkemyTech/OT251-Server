package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.contact.ContactRequest;
import com.alkemy.ong.dto.response.contact.ContactResponse;

public interface IContactService {

    public ContactResponse save(ContactRequest contactRequest);
}
