package com.astontech.hr.services;

import com.astontech.hr.domain.ElementType;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */
public interface ElementTypeService {

    Iterable<ElementType> listAllElementTypes();
    ElementType getElementTypeById(Integer id);
    ElementType saveElementType(ElementType elementType);
    Iterable<ElementType> saveElementTypesList(Iterable<ElementType> elementTypeIterable);
    void deleteElementType(Integer id);

    ElementType findByElementTypeName(String elementTypeName);
    List<ElementType> findAllByElementTypeName (String elementTypeName);
    List<ElementType> findAllByElementTypeNameIgnoreCase(String elementTypeName);
}
