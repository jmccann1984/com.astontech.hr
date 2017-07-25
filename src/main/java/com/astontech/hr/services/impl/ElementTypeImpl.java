package com.astontech.hr.services.impl;

import com.astontech.hr.domain.ElementType;
import com.astontech.hr.repositories.ElementTypeRep;
import com.astontech.hr.services.ElementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */
@Service
public class ElementTypeImpl implements ElementTypeService {

    @Autowired
    private ElementTypeRep elementTypeRep;

    @Override
    public Iterable<ElementType> listAllElementTypes() {
        return elementTypeRep.findAll();
    }

    @Override
    public ElementType getElementTypeById(Integer id) {
        return elementTypeRep.findOne(id);
    }

    @Override
    public ElementType saveElementType(ElementType elementType) {
        return elementTypeRep.save(elementType);
    }

    @Override
    public Iterable<ElementType> saveElementTypesList(Iterable<ElementType> elementTypeIterable) {
        return elementTypeRep.save(elementTypeIterable);
    }

    @Override
    public void deleteElementType(Integer id) {
        elementTypeRep.delete(id);
    }

    @Override
    public ElementType findByElementTypeName(String elementTypeName) {
        return elementTypeRep.findByElementTypeName(elementTypeName);
    }

    @Override
    public List<ElementType> findAllByElementTypeName(String elementTypeName) {
        return elementTypeRep.findAllByElementTypeName(elementTypeName);
    }

    @Override
    public List<ElementType> findAllByElementTypeNameIgnoreCase(String elementTypeName) {
        return elementTypeRep.findAllByElementTypeNameIgnoreCase(elementTypeName);
    }
}
