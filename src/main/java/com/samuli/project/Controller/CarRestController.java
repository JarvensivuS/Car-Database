package com.samuli.project.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samuli.project.CustomExceptions.CarNotFoundException;
import com.samuli.project.CustomExceptions.DatabaseEmptyException;
import com.samuli.project.Data.Car;
import com.samuli.project.Repository.CarRepository;
import jakarta.annotation.PostConstruct;

@RestController
public class CarRestController {

    @Autowired
    CarRepository carRepository;

    //Initialize database with cars on start. Optional.
    /* @PostConstruct
    public void init() 
    {
        carRepository.saveAll(List.of(
        new Car("Volvo","V60","KRO-569"),
        new Car("Tesla","Model Y","TSL-242"),
        new Car("BMW","M4","TNU-071"),
        new Car("Toyota","Corolla","UTS-921"),
        new Car("Mercedes-Benz","E200","MRC-610"),
        new Car("Subaru","WRX","FSC-723")));
    } */
   
    @GetMapping("/cars")
    public List<String> getCars() throws DatabaseEmptyException
    {
        Long iterator = 1L;
        List<String> carList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();

        if (carRepository.findAll().isEmpty()){
            carList.add(0, "Database empty!");
            DatabaseEmptyException exception = new DatabaseEmptyException("No cars found in database!");
            errorList.add(exception.toString());
            System.out.println(exception.toString());
            return errorList;
        }
        for (int i = 0; i < carRepository.count(); i++) {
            carList.add(i, carRepository.findByID(iterator));
            iterator++;
        }
        return carList;
    }

    @GetMapping("/cars/{id}")
    public String getCar(@PathVariable(value ="id")Long id) throws CarNotFoundException
    {
        if (carRepository.findByID(id) == null) {
            CarNotFoundException exception = new CarNotFoundException("Car with the id of " + id + " not found!");
            System.out.println(exception.toString());
            return exception.toString();

        }
        return carRepository.findByID(id);
    } 
    
    @PostMapping("addCar")
    public String addCar (
            @RequestParam("inputmake") String make, 
            @RequestParam("inputmodel") String model,
            @RequestParam("inputplate") String plateNumber) 
        {
            Car car2 = new Car();
            car2.setMake(make);
            car2.setModel(model);
            car2.setPlateNumber(plateNumber);
            carRepository.save(car2);
            return "car id " + car2.getId() + " added succesfully!" ;
        }
        
        //Workaround for @DeleteMapping, HTML form only supports @POST or @GET methods.
        @PostMapping("deleteCar")
        public String deleteCar(@RequestParam("deleteid")Long id) throws CarNotFoundException
         {
            if (carRepository.findByID(id) != null) {
                carRepository.deleteCar(id);
                return "car with id of  " + id + " deleted succesfully!";
            }
            CarNotFoundException exception = new CarNotFoundException("Car with the id of " + id + " doesnt exist!");
            System.out.println(exception.toString());
            return exception.toString();
        }
}