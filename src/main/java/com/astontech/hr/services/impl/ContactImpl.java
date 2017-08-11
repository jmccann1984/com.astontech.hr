package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Contact;
import com.astontech.hr.repositories.ContactRep;
import com.astontech.hr.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactImpl implements ContactService{

    @Autowired
    ContactRep contactRep;

    @Override
    public Iterable<Contact> listAllContacts() {
        return contactRep.findAll();
    }

    @Override
    public Contact getContactById(Integer id) {
        return contactRep.findOne(id);
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRep.save(contact);
    }

    @Override
    public Iterable<Contact> saveContactList(Iterable<Contact> contactList) {
        return contactRep.save(contactList);
    }

    @Override
    public void deleteContact(Integer id) {
        contactRep.delete(id);
    }
}
