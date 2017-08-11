package com.astontech.hr.repositories;

import com.astontech.hr.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRep extends CrudRepository<Contact, Integer>{
}
