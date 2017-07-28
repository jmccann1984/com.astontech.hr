package com.astontech.hr.controllers;

import com.astontech.hr.domain.Element;
import com.astontech.hr.domain.ElementType;
import com.astontech.hr.domain.VO.ElementVO;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.services.ElementTypeService;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.util.Elements;
import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 7/25/2017.
 */
@Controller
public class AdminController {

    @Autowired
    private ElementTypeService elementTypeService;
    @Autowired
    private VehicleMakeService vehicleMakeService;
    @Autowired
    private VehicleModelService vehicleModelSevice;


    private Logger log = Logger.getLogger(AdminController.class);

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome() {
        return "admin/adminHome";
    }


    //region ELEMENT
    @RequestMapping(value = "/admin/element/add", method = RequestMethod.GET)
    public String adminElement(Model model) {
        model.addAttribute("elementVO", new ElementVO());
        return "admin/element/element_add";
    }

    @RequestMapping(value = "/admin/element/add", method = RequestMethod.POST)
    public String adminElementPost(ElementVO elementVO, Model model){
        elementVO.splitNewElementsIntoArray();
        logElementVO(elementVO);
        saveElementTypeAndElementsFromVO(elementVO);

        boolean success = true;
        if(success) model.addAttribute("successAlert", "visible");
        else model.addAttribute("errorAlert", "visible");
        model.addAttribute("elementVO", new ElementVO());

        return "admin/element/element_add";
    }

    @RequestMapping(value = "/admin/element/list", method = RequestMethod.GET)
    public String adminElementList(Model model) {
        model.addAttribute("elementTypeList", elementTypeService.listAllElementTypes());

        return "admin/element/element_list";
    }

    @RequestMapping(value = "admin/element/edit/{id}", method = RequestMethod.GET)
    public String elementTypeEdit(@PathVariable int id, Model model){
        ElementType elementType = elementTypeService.getElementTypeById(id);

        model.addAttribute("elementType", elementType);
        return "admin/element/element_edit";
    }

    @RequestMapping(value = "/admin/element/update", method = RequestMethod.POST)
    public String elementTypeUpdate(ElementType elementType, Model model, @RequestParam("inputNewElement") String newElement) {
        //notes:    if newElement has a value, add it to the list
        if(!newElement.equals("")) {
            if (elementType.getElementList() == null) elementType.setElementList(new ArrayList<Element>());
            elementType.getElementList().add(new Element(newElement));
        }

        //notes:    iterate through the list of elements
        for(int i=0; i < elementType.getElementList().size(); i++){
            //notes:    check to see if element name is empty
            if(elementType.getElementList().get(i).getElementName().equals("")){
                //notes:    element name is blank, remove it from the list
                elementType.getElementList().remove(i);
            }
        }
        elementTypeService.saveElementType(elementType);
        model.addAttribute("successAlert", "visible");
        return "redirect:/admin/element/edit/"+elementType.getId();
    }

    @RequestMapping(value = "admin/element/delete/{id}", method = RequestMethod.GET)
    public String elementTypeDelete(@PathVariable int id, Model model){
        elementTypeService.deleteElementType(id);

        return "redirect:/admin/element/list";
    }
    //endregion

    //region VEHICLE
    //todo: listAll
    @RequestMapping(value = "admin/vehicle/list", method = RequestMethod.GET)
    public String adminVehilceList(Model model) {
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMake());
        return "admin/vehicle/vehicle_list";
    }

    //todo: VehicleAdd
    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
    public String adminVehicleAdd(Model model) {
        model.addAttribute("");
        return "admin/vehicle/vehicle_add";
    }

    //todo: vehicleMakeAdd
    @RequestMapping(value = "/admin/vehicle/make/add", method = RequestMethod.GET)
    public String adminVehicleMakeAdd(Model model) {
        model.addAttribute("");
        return "admin/vehicle/vehicle_make_add";
    }

    //todo: vehicleMakeEdit
    @RequestMapping(value = "/admin/vehicle/make/{id}", method = RequestMethod.GET)
    public String adminVehicleMakeEdit(@PathVariable int id, Model model){
        return "admin/vehicle/vehicle_make_edit";
    }

    //todo: vehicleModelEdit
    @RequestMapping(value = "/admin/vehicle/model/{id}", method = RequestMethod.GET)
    public String adminVehicleModelEdit(@PathVariable int id, Model model){
        return "admin/vehicle/vehicle_model_edit";
    }
    //todo: vehicleEdit
    @RequestMapping(value = "/admin/vehicle/{id}", method = RequestMethod.GET)
    public String adminVehicleEdit(@PathVariable int id, Model model){
        return "admin/vehicle/vehicle_edit";
    }

    //todo: vehicleMakeDel
    @RequestMapping(value = "/admin/vehicle/make/del/{id}", method = RequestMethod.GET)
    public String adminVehicleMakeDelete(@PathVariable int id, Model model){
        vehicleMakeService.deleteVehicleMake(id);
        return "redirect:/admin/vehicle/list";
    }

    //todo: vehicleModelDel
    @RequestMapping(value = "/admin/vehicle/make{makeId}/model/del/{modelId}", method = RequestMethod.GET)
    public String adminVehicleMakeDelete(@PathVariable int makeId, @PathVariable int modelId, Model model){
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(makeId);
        vehicleMake.getVehicleModelList().remove(modelId);
        vehicleMakeService.saveVehicleMake(vehicleMake);

        return "redirect:/admin/vehicle/list";
    }

    //endregion

    //region HELPER METHODS

    private void saveElementTypeAndElementsFromVO(ElementVO elementVO) {
        List<Element> newElementList = new ArrayList<>();
        for(String str : elementVO.getNewElementArray()){
            newElementList.add(new Element(str));
        }
        ElementType newElementType = new ElementType(elementVO.getNewElementType());
        newElementType.setElementList(newElementList);

        elementTypeService.saveElementType(newElementType);
    }

    private void logElementVO(ElementVO elementVO){
        log.info("New Element Type: " + elementVO.getNewElementType());

        for(String str : elementVO.getNewElementArray()){
            log.info("New Elements: " + str);
        }
    }

    //endregion
}
