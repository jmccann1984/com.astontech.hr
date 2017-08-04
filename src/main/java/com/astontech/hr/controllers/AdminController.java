package com.astontech.hr.controllers;

import com.astontech.hr.domain.*;
import com.astontech.hr.domain.VO.ElementVO;
import com.astontech.hr.domain.VO.VehicleMakeVO;
import com.astontech.hr.services.ElementTypeService;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import com.astontech.hr.services.VehicleService;
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
    private VehicleModelService vehicleModelService;
    @Autowired
    private VehicleService vehicleService;


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
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMake());
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModel());
        model.addAttribute("doThis", "add");
        return "admin/vehicle/vehicle_edit";
    }

    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
    public String adminVehicleAddNew(Model model, Vehicle vehicle, @RequestParam("vMIdSel") Integer vehicleModelId) {
        vehicle.setVehicleModel(vehicleModelService.getVehicleModelById(vehicleModelId));
        vehicleService.saveVehicle(vehicle);
        return "redirect:/admin/vehicle/"+vehicle.getId();
    }

    //todo: vehicleMakeAdd
    @RequestMapping(value = "/admin/vehicle/make/add", method = RequestMethod.GET)
    public String adminVehicleMakeAdd(Model model) {
        model.addAttribute("VehicleMakeVO", new VehicleMakeVO());
        return "admin/vehicle/vehicle_make_add";
    }

    @RequestMapping(value = "/admin/vehicle/make/add", method = RequestMethod.POST)
    public String adminVehicleMakePost(Model model, VehicleMakeVO vehicleMakeVO) {
        vehicleMakeVO.SplitVehicleModelsToArray();
        saveVehicleMakeAndModelsFromVO(vehicleMakeVO);

        return "redirect:/admin/vehicle/make/" + vehicleMakeService.findByVehicleMakeName(vehicleMakeVO.getNewVehicleMakeName()).getId();
    }

    //todo: vehicleMakeEdit
    @RequestMapping(value = "/admin/vehicle/make/{id}", method = RequestMethod.GET)
    public String adminVehicleMakeEdit(@PathVariable int id, Model model){
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(id);
        model.addAttribute("vehicleMake", vehicleMake);

        return "admin/vehicle/vehicle_make_edit";
    }

    @RequestMapping(value = "/admin/vehicle/make/update", method = RequestMethod.POST)
    public String adminVehicleMakeUpdate(Model model, VehicleMake vehicleMake, @RequestParam("newVehicleModel") String newVehicleModelString) {
        //attach a new Model List if the Make doesn't have one
        if(vehicleMake.getVehicleModelList()==null) vehicleMake.setVehicleModelList(new ArrayList<VehicleModel>());

        //Iterate through entire Model List
        //if the Model name has been cleared, delete the Model from the Database and remove the Model from the Model List
        for(int i =0; i<vehicleMake.getVehicleModelList().size(); i++){
            if (vehicleMake.getVehicleModelList().get(i).getVehicleModelName().equals("")) {
                vehicleModelService.deleteVehicleModel(vehicleMake.getVehicleModelList().get(i).getId());
                vehicleMake.getVehicleModelList().remove(i);
            }
        }

        //reattach all of the remaining Models in the Model List to the Make
        for(VehicleModel vehicleModel : vehicleMake.getVehicleModelList()){
            vehicleModel.setVehicleMake(vehicleMake);
        }

        //add the new Model to the Model List
        if(!newVehicleModelString.equals("")){
            VehicleModel newVehicleModel = new VehicleModel(newVehicleModelString);
            newVehicleModel.setVehicleMake(vehicleMake);
            vehicleMake.getVehicleModelList().add(newVehicleModel);
        }

        //resave the Make
        vehicleMakeService.saveVehicleMake(vehicleMake);

        return "redirect:/admin/vehicle/make/" + vehicleMake.getId();
    }

    //todo: vehicleModelEdit
    @RequestMapping(value = "/admin/vehicle/model/{id}", method = RequestMethod.GET)
    public String adminVehicleModelEdit(@PathVariable int id, Model model){
        VehicleModel vehicleModel = vehicleModelService.getVehicleModelById(id);
        model.addAttribute("vehicleModel", vehicleModel);
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMake());
        model.addAttribute("selectedMake", vehicleModel.getVehicleMake());

        return "admin/vehicle/vehicle_model_edit";
    }

    @RequestMapping(value = "/admin/vehicle/model/update", method = RequestMethod.POST)
    public String adminVehicleModelUpdate(Model model, VehicleModel vehicleModel, VehicleMake vehicleMake, @RequestParam("vMIdSel") Integer vehicleMakeId, @RequestParam("inputNewVehicle") String newVehicleString) {
        //reattach the Make to the Model or attach a new Make to the Model based off selection box
        if(vehicleMake.getId().equals(vehicleMakeId)  || vehicleMakeId==0) vehicleModel.setVehicleMake(vehicleMake);
        else vehicleModel.setVehicleMake(vehicleMakeService.getVehicleMakeById(vehicleMakeId));

        //attach a new VehicleList if the Model doesn't have one
        if(vehicleModel.getVehicleList()==null) vehicleModel.setVehicleList(new ArrayList<Vehicle>());

        //Iterate through entire Model's Vehicle List
        //if the VIN has been cleared, delete the Vehicle from the Database and remove the Vehicle from the Model's Vehicle List
        for(int i =0; i<vehicleModel.getVehicleList().size(); i++){
            if (vehicleModel.getVehicleList().get(i).getVin().equals("")) {
                vehicleService.deleteVehicle(vehicleModel.getVehicleList().get(i).getId());
                vehicleModel.getVehicleList().remove(i);
            }
        }

        //reattach all of the remaining Vehicles in the Model's Vehicle List to the Model
        for(Vehicle vehicle : vehicleModel.getVehicleList()){
            vehicle.setVehicleModel(vehicleModel);
        }

        //add the new Vehicle to the Model's Vehicle List
        if(!newVehicleString.equals(",,,")) {
            String[] newVehicleArray = newVehicleString.split(",");
            Vehicle newVehicle = new Vehicle(newVehicleArray[0], newVehicleArray[1], Integer.parseInt(newVehicleArray[2]), newVehicleArray[3]);
            newVehicle.setVehicleModel(vehicleModel);
            vehicleModel.getVehicleList().add(newVehicle);
        }

        //resave the model
        vehicleModelService.saveVehicleModel(vehicleModel);

        return "redirect:/admin/vehicle/model/" + vehicleModel.getId();
    }

    //todo: vehicleEdit
    @RequestMapping(value = "/admin/vehicle/{id}", method = RequestMethod.GET)
    public String adminVehicleEdit(@PathVariable int id, Model model){
        Vehicle vehicle = vehicleService.getVehicleById(id);

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("vehicleMakeList", vehicleMakeService.listAllVehicleMake());
        model.addAttribute("vehicleModelList", vehicleModelService.listAllVehicleModel());
        model.addAttribute("selectedMake", vehicle.getVehicleModel().getVehicleMake());
        model.addAttribute("selectedModel", vehicle.getVehicleModel());
        model.addAttribute("doThis", "update");

        return "admin/vehicle/vehicle_edit";
    }

    @RequestMapping(value = "/admin/vehicle/update", method = RequestMethod.POST)
    public String adminVehicleUpdate(Model model, Vehicle vehicle, VehicleModel vehicleModel, @RequestParam("vMIdSel") Integer vehicleModelId) {
        if(vehicleModel.getId().equals(vehicleModelId) || vehicleModelId == 0) vehicle.setVehicleModel(vehicleModel);
        else vehicle.setVehicleModel(vehicleModelService.getVehicleModelById(vehicleModelId));
        vehicleService.saveVehicle(vehicle);

        return "redirect:/admin/vehicle/" + vehicle.getId();
    }

    //todo: vehicleMakeDel
    @RequestMapping(value = "/admin/vehicle/make/del/{id}", method = RequestMethod.GET)
    public String adminVehicleMakeDelete(@PathVariable int id, Model model){
        vehicleMakeService.deleteVehicleMake(id);
        return "redirect:/admin/vehicle/list";
    }

    //todo: vehicleModelDel
    @RequestMapping(value = "/admin/vehicle/model/del/{id}", method = RequestMethod.GET)
    public String adminVehicleModelDelete(@PathVariable int id, Model model){
        vehicleModelService.deleteVehicleModel(id);
        return "redirect:/admin/vehicle/list";
    }

    //todo: vehicleDel
    @RequestMapping(value = "/admin/vehicle/del/{id}", method = RequestMethod.GET)
    public String adminVehicleDelete(@PathVariable int id, Model model){
        vehicleService.deleteVehicle(id);
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

    private void saveVehicleMakeAndModelsFromVO(VehicleMakeVO vehicleMakeVO){
        List<VehicleModel> newVehicleModelList = new ArrayList<>();

        VehicleMake newVehicleMake = new VehicleMake(vehicleMakeVO.getNewVehicleMakeName());
        for(String str : vehicleMakeVO.getNewVehicleModelArray()) newVehicleModelList.add(new VehicleModel(str, new ArrayList<Vehicle>(), newVehicleMake));
        newVehicleMake.setVehicleModelList(newVehicleModelList);

        vehicleMakeService.saveVehicleMake(newVehicleMake);
    }

    private void logElementVO(ElementVO elementVO){
        log.info("New Element Type: " + elementVO.getNewElementType());

        for(String str : elementVO.getNewElementArray()){
            log.info("New Elements: " + str);
        }
    }

    //endregion
}
