package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.*;
import com.astontech.hr.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.jta.bitronix.BitronixDependentBeanFactoryPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua.McCann on 7/24/2017.
 */
@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent>{


    @Autowired
    private ElementTypeService elementTypeService;
    @Autowired
    private VehicleMakeService vehicleMakeService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private AddressService addressService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateElementAndElementTypes();
        generateVehicleMakeModelAndVehicleData();
        generateEmployees();
        generateContactAndAddressData();
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
        masterList.add(new ElementType("Home"));
        masterList.add(new ElementType("Work"));
        masterList.add(new ElementType("Address"));

        masterList.add(programmingLanguage);

        elementTypeService.saveElementTypesList(masterList);



    }

    private void generateVehicleMakeModelAndVehicleData(){
        List<VehicleMake> vehicleMakeList = new ArrayList<>();
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        List<Vehicle> vehicleList1 = new ArrayList<>();
        List<Vehicle> vehicleList2 = new ArrayList<>();

        vehicleList1.add(new Vehicle("ABC-123", "1HGYNRXV2435NXJ", 1984, "Red"));
        vehicleList1.add(new Vehicle("ABC-456", "1HGYNRXV2435NXJ", 1985, "Green"));
        vehicleList1.add(new Vehicle("ABC-789", "1HGYNRXV2435NXJ", 1986, "Yellow"));
        vehicleList1.add(new Vehicle("ABD-123", "1HGYNRXV2435NXJ", 1987, "Blue"));
        vehicleList1.add(new Vehicle("ABD-456", "1HGYNRXV2435NXJ", 1988, "Orange"));

        vehicleList2.add(new Vehicle("ABD-789", "1HGYNRXV2435NVJ", 1984, "Red"));
        vehicleList2.add(new Vehicle("ABE-123", "1HGYNRXV2435NVJ", 1985, "Green"));
        vehicleList2.add(new Vehicle("ABE-456", "1HGYNRXV2435NVJ", 1986, "Yellow"));
        vehicleList2.add(new Vehicle("ABE-789", "1HGYNRXV2435NVJ", 1987, "Blue"));

        vehicleModelList.add(new VehicleModel("Camry", vehicleList1));
        vehicleModelList.add(new VehicleModel("Carola", vehicleList2));
        for(Vehicle vehicle : vehicleList1){
            vehicle.setVehicleModel(vehicleModelList.get(0));
        }
        for(Vehicle vehicle : vehicleList2){
            vehicle.setVehicleModel(vehicleModelList.get(1));
        }

        vehicleMakeList.add(new VehicleMake("Toyota", vehicleModelList));

        for(VehicleModel vehicleModel : vehicleModelList){
            vehicleModel.setVehicleMake(vehicleMakeList.get(0));
        }

        vehicleModelList = new ArrayList<>();
        vehicleList1 = new ArrayList<>();
        vehicleList2 = new ArrayList<>();

        vehicleList1.add(new Vehicle("ABF-123", "1FNYNRXV2435NXJ", 1984, "Red"));
        vehicleList1.add(new Vehicle("ABF-456", "1FNYNRXV2435NXJ", 1985, "Green"));
        vehicleList1.add(new Vehicle("ABF-789", "1FNYNRXV2435NXJ", 1986, "Yellow"));
        vehicleList1.add(new Vehicle("ABG-123", "1FNYNRXV2435NXJ", 1987, "Blue"));
        vehicleList1.add(new Vehicle("ABG-456", "1FNYNRXV2435NXJ", 1988, "Orange"));

        vehicleList2.add(new Vehicle("ABG-789", "1FNYNRXV2435NVJ", 1984, "Red"));
        vehicleList2.add(new Vehicle("ABH-123", "1FNYNRXV2435NVJ", 1985, "Green"));
        vehicleList2.add(new Vehicle("ABH-456", "1FNYNRXV2435NVJ", 1986, "Yellow"));
        vehicleList2.add(new Vehicle("ABH-789", "1FNYNRXV2435NVJ", 1987, "Blue"));

        vehicleModelList.add(new VehicleModel("Sorento", vehicleList1));
        vehicleModelList.add(new VehicleModel("Rio", vehicleList2));

        for(Vehicle vehicle : vehicleList1){
            vehicle.setVehicleModel(vehicleModelList.get(0));
        }
        for(Vehicle vehicle : vehicleList2){
            vehicle.setVehicleModel(vehicleModelList.get(1));
        }

        vehicleMakeList.add(new VehicleMake("Kia", vehicleModelList));
        for(VehicleModel vehicleModel : vehicleModelList) {
            vehicleModel.setVehicleMake(vehicleMakeList.get(1));
        }


        vehicleModelList = new ArrayList<>();
        vehicleList1 = new ArrayList<>();
        vehicleList2 = new ArrayList<>();

        vehicleList1.add(new Vehicle("ABI-123", "1RNXNRXV2435NXJ", 1984, "Red"));
        vehicleList1.add(new Vehicle("ABI-456", "1RNXNRXV2435NXJ", 1985, "Green"));
        vehicleList1.add(new Vehicle("ABI-789", "1RNXNRXV2435NXJ", 1986, "Yellow"));
        vehicleList1.add(new Vehicle("ABJ-123", "1RNXNRXV2435NXJ", 1987, "Blue"));
        vehicleList1.add(new Vehicle("ABJ-456", "1RNXNRXV2435NXJ", 1988, "Orange"));

        vehicleList2.add(new Vehicle("ABJ-789", "1RNXNRXV2435NVJ", 1984, "Red"));
        vehicleList2.add(new Vehicle("ABK-123", "1RNXNRXV2435NVJ", 1985, "Green"));
        vehicleList2.add(new Vehicle("ABK-456", "1RNXNRXV2435NVJ", 1986, "Yellow"));
        vehicleList2.add(new Vehicle("ABK-789", "1RNXNRXV2435NVJ", 1987, "Blue"));

        vehicleModelList.add(new VehicleModel("Civic", vehicleList1));
        vehicleModelList.add(new VehicleModel("Accord", vehicleList2));

        for(Vehicle vehicle : vehicleList1){
            vehicle.setVehicleModel(vehicleModelList.get(0));
        }
        for(Vehicle vehicle : vehicleList2){
            vehicle.setVehicleModel(vehicleModelList.get(1));
        }

        vehicleMakeList.add(new VehicleMake("Honda", vehicleModelList));
        for(VehicleModel vehicleModel : vehicleModelList) {
            vehicleModel.setVehicleMake(vehicleMakeList.get(2));
        }


            vehicleMakeService.saveVehicleMakeList(vehicleMakeList);

    }

    private void generateEmployees() {

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName("Bipin");
        employee.setLastName("Butala");
        employee.setBackground("Java Developer");
        employeeList.add(employee);

        employee = new Employee();
        employee.setFirstName("Joshua");
        employee.setLastName("McCann");
        employee.setBackground("Java Developer");
        employeeList.add(employee);

        employee = new Employee();
        employee.setFirstName("Dan");
        employee.setLastName("Simmer");
        employee.setBackground(".Net Developer");
        employeeList.add(employee);

        employee = new Employee();
        employee.setFirstName("Bob");
        employee.setLastName("Dole");
        employee.setBackground("Senator");
        employeeList.add(employee);

        employee = new Employee();
        employee.setFirstName("Randy");
        employee.setLastName("Anderson");
        employee.setBackground("Sargent");
        employeeList.add(employee);

        employeeService.saveEmployeeList(employeeList);
    }

    private void generateContactAndAddressData() {

        Address BipinHome = new Address(1234, "Anywhere St", null, "Minneapolis", "MN", 55419, "USA");
        addressService.saveAddress(BipinHome);
//        BipinHome.setContactList(new ArrayList<Contact>());


        Contact BipinContact = new Contact();
//        BipinHome.getContactList().add(BipinContact);
        BipinContact.setAddressList(new ArrayList<Address>());
//        BipinContact.getAddressList().add(BipinHome);
        BipinContact.getAddressList().add(addressService.getAddressById(1));
        BipinContact.setEmployee(employeeService.getEmployeeById(1));
        contactService.saveContact(BipinContact);

        Contact JoshuaContact = new Contact();
        JoshuaContact.setAddressList(new ArrayList<Address>());
        Address JoshuaAddress = new Address(1019, "Chromwell Ave", null, "St Paul", "MN", 55114, "USA");
        addressService.saveAddress(JoshuaAddress);
        JoshuaContact.getAddressList().add(addressService.getAddressById(2));
        JoshuaContact.setEmployee(employeeService.getEmployeeById(2));
        contactService.saveContact(JoshuaContact);

        Contact DanContact = new Contact();
        DanContact.setAddressList(new ArrayList<Address>());
        DanContact.getAddressList().add(addressService.getAddressById(1));
        DanContact.setEmployee(employeeService.getEmployeeById(3));
        contactService.saveContact(DanContact);

        Contact BobContact = new Contact();
        BobContact.setAddressList(new ArrayList<Address>());
        Address BobHome = new Address(5678, "Random Ave", "#789", "St Paul", "MN", 55107, "USA");
        addressService.saveAddress(BobHome);
        BobContact.getAddressList().add(BobHome);
        BobContact.setEmployee(employeeService.getEmployeeById(4));
        contactService.saveContact(BobContact);

        Contact RandyContact = new Contact();
        RandyContact.setAddressList(new ArrayList<Address>());
        Address RandyHome = new Address(9121, "Somewhere Pl", null, "Stillwater", "MN", 55001, "USA");
        addressService.saveAddress(RandyHome);
        RandyContact.getAddressList().add(RandyHome);
        RandyContact.setEmployee(employeeService.getEmployeeById(5));
        contactService.saveContact(RandyContact);

    }
}
