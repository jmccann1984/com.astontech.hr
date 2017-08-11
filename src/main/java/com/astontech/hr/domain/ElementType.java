package com.astontech.hr.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */

@Entity
public class ElementType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ElementTypeId")
    private Integer id;

    private String elementTypeName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Element> elementList;

    @Version
    private Integer version;

    public ElementType() {
    }

    public ElementType(String elementTypeName) {
        this.elementTypeName = elementTypeName;
    }

    public ElementType(String elementTypeName, List<Element> elementList) {
        this.elementTypeName = elementTypeName;
        this.elementList = elementList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElementTypeName() {
        return elementTypeName;
    }

    public void setElementTypeName(String elementTypeName) {
        this.elementTypeName = elementTypeName;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
