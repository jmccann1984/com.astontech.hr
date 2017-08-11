package com.astontech.hr.repositories;

import com.astontech.hr.domain.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRep extends CrudRepository<Address, Integer>{
}
