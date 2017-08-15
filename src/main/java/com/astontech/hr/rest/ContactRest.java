package com.astontech.hr.rest;

import com.astontech.hr.domain.Contact;
import com.astontech.hr.services.ContactService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactRest {

    Logger log = Logger.getLogger(EmployeeRest.class);

    @Autowired
    private ContactService contactService;

    // GET ALL
    @RequestMapping(value="/", method = RequestMethod.GET)
    public Iterable<Contact> getAll() {
        return contactService.listAllContacts();
    }

    // GET BY ID
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Contact getByeId(@PathVariable int id){
        return contactService.getContactById(id);
    }

    // SAVE
    @RequestMapping(value="/", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact){
        return contactService.saveContact(contact);
    }

    // DELETE
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable int id) {
        boolean result = false;
        try{
            contactService.deleteContact(id);
            result = true;
        } catch (Exception ex) {
            log.info(ex);
        }

        return result;
    }
}
