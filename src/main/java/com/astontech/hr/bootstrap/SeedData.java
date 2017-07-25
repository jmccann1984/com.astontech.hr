package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */
@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateElementAndElementTypes();
    }

    private void generateElementAndElementTypes() {
        List<ElementType> masterList = new ArrayList<>();
        ElementType laptopType = new ElementType("Laptop");

        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Acer"));
        elementList.add(new Element("Dell"));
        elementList.add(new Element("Apple"));
        elementList.add(new Element("Samsung"));
        elementList.add(new Element("Asus"));

        laptopType.setElementList(elementList);

        masterList.add(laptopType);

        ElementType carType = new ElementType("Car");
        elementList = new ArrayList<>();
        elementList.add(new Element("Toyota"));
        elementList.add(new Element("Honda"));
        elementList.add(new Element("Hyundai"));
        elementList.add(new Element("General Motors"));
        elementList.add(new Element("Kia"));

        carType.setElementList(elementList);

        masterList.add(carType);

        ElementType programmingLanguage = new ElementType("Programming Language");
        elementList = new ArrayList<>();
        elementList.add(new Element("Pascal"));
        elementList.add(new Element("C++"));
        elementList.add(new Element("Java"));
        elementList.add(new Element("C#"));
        elementList.add(new Element("F#"));

        programmingLanguage.setElementList(elementList);

        masterList.add(programmingLanguage);

        elementTypeService.saveElementTypesList(masterList);



    }
}
