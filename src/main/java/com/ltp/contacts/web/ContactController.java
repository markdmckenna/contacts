package com.ltp.contacts.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.service.ContactService;



@RestController
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        LOGGER.info("[IN]ContactController - getContact - id: {}", id);
        Contact contact;
        contact = contactService.getContactById(id);
        if (null == contact)  return new ResponseEntity<>(contact, HttpStatus.NOT_FOUND);
        LOGGER.info("[OUT]ContactController - getContact - contact: {}", contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<HttpStatus> createContact(@Valid @RequestBody Contact contact) {
        LOGGER.info("[IN]ContactController - creatContact - contact: {}", contact);
        contactService.saveContact(contact);
        LOGGER.info("[OUT]ContactController - creatContact");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @Valid @RequestBody Contact contact) {
        LOGGER.info("[IN]ContactController - updateContact - id: {} - contact: {}", id, contact);
        contactService.updateContact(id, contact);          
        LOGGER.info("[OUT]ContactController - updateContact - contact: {}", contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        LOGGER.info("[IN]ContactController - deleteContact - id: {}", id);
        contactService.deleteContact(id);
        LOGGER.info("[OUT]ContactController - deleteContact");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>>  getContacts() {
        LOGGER.info("[IN]ContactController - getContacts");
        List<Contact> contacts = contactService.getContacts();
        if (CollectionUtils.isEmpty(contacts)) {
            LOGGER.info("[OUT]ContactController - getContacts - contacts empty: {}", contacts);
            return new ResponseEntity<>(contacts, HttpStatus.NO_CONTENT);
        } 
        LOGGER.info("[OUT]ContactController - getContacts - contacts: {}", contacts);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    

}
