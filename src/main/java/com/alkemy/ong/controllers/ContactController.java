package com.alkemy.ong.controllers;

import com.alkemy.ong.dto.request.contact.ContactRequest;
import com.alkemy.ong.dto.response.contact.ContactResponse;
import com.alkemy.ong.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponse> createContact(@RequestBody @Valid ContactRequest contactRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contactRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ContactResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getAll());
    }
}
