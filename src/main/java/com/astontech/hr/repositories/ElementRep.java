package com.astontech.hr.repositories;

import com.astontech.hr.domain.Element;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/21/2017.
 */
public interface ElementRep extends CrudRepository<Element, Integer> {

    //will auto generate the required code form the structure of the method name
    Element findByElementName(String elementName);
    List<Element> findAllByElementName (String elementName);
    List<Element> findAllByElementNameIgnoreCase(String elementName);
    Element findFirstByOrderByElementNameAsc();
    Element findFirstByOrderByElementNameDesc();
    int countByElementName(String elementName);
    List<Element> findByElementNameIgnoreCaseLike(String elementName);
    List<Element> findByElementNameIgnoreCaseContaining(String elementName);
}
