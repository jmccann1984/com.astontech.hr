package com.astontech.hr.rest;

import com.astontech.hr.domain.Address;
import com.astontech.hr.services.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/address")
public class AddressRest {

    Logger log = Logger.getLogger(Address.class);

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Address> getAll() { return addressService.listAllAddresses(); }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Address getAddress(@PathVariable Integer id){ return addressService.getAddressById(id); }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Address saveAddress(@RequestBody Address address) { return addressService.saveAddress(address); }

    @RequestMapping(value="/m", method = RequestMethod.POST)
    public Iterable<Address> saveAddressList(@RequestBody Iterable<Address> addressList) { return addressService.saveAddressList(addressList); }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public Boolean deleteAddress(@PathVariable Integer id) {
        boolean success = false;
        try {
            addressService.deleteAddress(id);
            success = true;
        } catch (Exception e) {
            log.error(e);
        }

        return success;
    }
}
