package com.ltp.contacts.service;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ltp.contacts.exception.ContactNotFoundException;
import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

     private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    //@Autowired
    private ContactRepository contactRepository;
    
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact getContactById(String id) {
        LOGGER.info("[IN]ContactServiceImpl - getContactById - id: {}", id);
        Contact contact = contactRepository.getContact(this.findIndexById(id));
        LOGGER.info("[OUT]ContactServiceImpl - getContactById - contact: {}", contact);
        return contact;
    }
    
    @Override
    public void saveContact(Contact contact) {
      contactRepository.saveContact(contact);
    }

    @Override
    public void updateContact(String id, Contact contact) {
        contactRepository.updateContact(findIndexById(id), contact);
    }

    @Override
    public void deleteContact(String id) {
        contactRepository.deleteContact(findIndexById(id));
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    private int findIndexById(String id) throws ContactNotFoundException {
        return IntStream.range(0, contactRepository.getContacts().size())
            .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ContactNotFoundException(id));
    }


}
