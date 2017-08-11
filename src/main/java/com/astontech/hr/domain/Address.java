package com.astontech.hr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "addressId")
    private Integer id;

    @Version
    private Integer version;

    @Column(name="addressNumber")
    private Integer addressNumber;

    private String street;
    private String street02;
    private String city;
    private String state;
    private Integer zip;
    private String country;

    //region CONSTRUCTORS
    public Address() {
    }

    public Address(Integer addressNumber, String street, String street02, String city, String state, Integer zip, String country) {
        this.addressNumber = addressNumber;
        this.street = street;
        this.street02 = street02;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    //endregion

    //region GETTERS/SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet02() {
        return street02;
    }

    public void setStreet02(String street02) {
        this.street02 = street02;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    //endregion
}
