package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Address;
import com.astontech.hr.repositories.AddressRep;
import com.astontech.hr.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressImpl implements AddressService{

    @Autowired
    AddressRep addressRep;

    @Override
    public Iterable<Address> listAllAddresses() {
        return addressRep.findAll();
    }

    @Override
    public Address getAddressById(Integer id) {
        return addressRep.findOne(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRep.save(address);
    }

    @Override
    public Iterable<Address> saveAddressList(Iterable<Address> addresses) {
        return addressRep.save(addresses);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRep.delete(id);
    }
}
