package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Element;
import com.astontech.hr.repositories.ElementRep;
import com.astontech.hr.services.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */

@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    private ElementRep elementRep;

    @Override
    public Iterable<Element> listAllElements() {
        return elementRep.findAll();
    }

    @Override
    public Element getElementById(Integer id) {
        return elementRep.findOne(id);
    }

    @Override
    public Element saveElement(Element element) {
        return elementRep.save(element);
    }

    @Override
    public Iterable<Element> saveElementList(Iterable<Element> elementIterable) {
        return elementRep.save(elementIterable);
    }

    @Override
    public void deleteElement(Integer id) {
        elementRep.delete(id);
    }

    @Override
    public Element findByElementName(String elementName) {
        return elementRep.findByElementName(elementName);
    }

    @Override
    public List<Element> findAllByElementName(String elementName) {
        return elementRep.findAllByElementName(elementName);
    }

    @Override
    public List<Element> findAllByElementNameIgnoreCase(String elementName) {
        return elementRep.findAllByElementNameIgnoreCase(elementName);
    }

    @Override
    public Element findFirstByOrderByElementNameAsc() {
        return elementRep.findFirstByOrderByElementNameAsc();
    }

    @Override
    public Element findFirstByOrderByElementNameDesc() {
        return elementRep.findFirstByOrderByElementNameDesc();
    }

    @Override
    public int countByElementName(String elementName) {
        return elementRep.countByElementName(elementName);
    }

    @Override
    public List<Element> findByElementNameIgnoreCaseLike(String elementName) {
        return elementRep.findByElementNameIgnoreCaseLike(elementName);
    }

    @Override
    public List<Element> findByElementNameIgnoreCaseContaining(String elementName) {
        return elementRep.findByElementNameIgnoreCaseContaining(elementName);
    }
}
