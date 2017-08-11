package com.astontech.hr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee extends Person{

    private String background;

    @OneToMany
    private List<Project> projects;

//    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
//    private List<Contact> contactList;

    //region CONSTRUCTORS
    public Employee() {
    }

    //endregion

    //region GETTERS/SETTERS

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

//    public List<Contact> getContactList() {
//        return contactList;
//    }
//
//    public void setContactList(List<Contact> contactList) {
//        this.contactList = contactList;
//    }


    //endregion
}
