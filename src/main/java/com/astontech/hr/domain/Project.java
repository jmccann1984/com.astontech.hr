package com.astontech.hr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ProjectId")
    private Integer id;

    @Version
    private Integer version;

    private String projectName;
    private String clientName;
    private Integer fieldRate;


    //region CONSTRUCTORS
    public Project() {
    }

    //endregion

    //region GETTERS/SETTERS
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getFieldRate() {
        return fieldRate;
    }

    public void setFieldRate(Integer fieldRate) {
        this.fieldRate = fieldRate;
    }

    //endregion
}
