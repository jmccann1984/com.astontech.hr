package com.astontech.hr.services;

import com.astontech.hr.domain.Element;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */
public interface ElementService {

    Iterable<Element> listAllElements();
    Element getElementById(Integer id);
    Element saveElement(Element element);
    Iterable<Element> saveElementList(Iterable<Element> elementIterable);
    void deleteElement(Integer id);

    //Custom repo methods
    Element findByElementName(String elementName);
    List<Element> findAllByElementName (String elementName);
    List<Element> findAllByElementNameIgnoreCase(String elementName);
    Element findFirstByOrderByElementNameAsc();
    Element findFirstByOrderByElementNameDesc();
    int countByElementName(String elementName);
    List<Element> findByElementNameIgnoreCaseLike(String elementName);
    List<Element> findByElementNameIgnoreCaseContaining(String elementName);


}
