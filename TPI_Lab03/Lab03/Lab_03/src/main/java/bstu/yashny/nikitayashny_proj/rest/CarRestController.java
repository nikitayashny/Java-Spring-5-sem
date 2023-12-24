package bstu.yashny.nikitayashny_proj.rest;

import bstu.yashny.nikitayashny_proj.dto.NameRequest;
import bstu.yashny.nikitayashny_proj.dto.CarRequestNoIdRent;
import bstu.yashny.nikitayashny_proj.dto.CarRequestNoRent;
import bstu.yashny.nikitayashny_proj.exception.ControllerException;
import bstu.yashny.nikitayashny_proj.exception.RepositoryException;
import bstu.yashny.nikitayashny_proj.exception.ServiceException;
import bstu.yashny.nikitayashny_proj.models.Car;
import bstu.yashny.nikitayashny_proj.repository.UserRentFormRepository;
import bstu.yashny.nikitayashny_proj.service.CarService;
import bstu.yashny.nikitayashny_proj.service.UserRentFormService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class CarRestController {
    @Autowired  // автоматический поиск бина
    private CarService carService;
    @Autowired
    private UserRentFormService userRentFormService;
    @Autowired
    private UserRentFormRepository userRentFormRepository;

    private static final Logger logger = Logger.getLogger(CarRestController.class);

    @PostMapping("/admin/createCar")
    public ResponseEntity<?> createCar(@RequestBody CarRequestNoIdRent carRequestNoIdRent) throws ControllerException {
        Car stuff = new Car(
                carRequestNoIdRent.getName(),
                carRequestNoIdRent.getDescription(),
                carRequestNoIdRent.getCost(),
                carRequestNoIdRent.getExpirationDate()
        );
        try {
            carService.create(stuff);
            return new ResponseEntity<>(stuff, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @DeleteMapping("/admin/deleteCarByNameA")
    public ResponseEntity<?> deleteCarByNameA(@RequestBody NameRequest nameRequest) throws ControllerException {
        try {
            Car man = carService.getByName(nameRequest.getName());
            carService.deleteByName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PutMapping("/admin/updateCar")
    public ResponseEntity<?> updateCar(@RequestBody CarRequestNoRent carRequestNoRent)throws ControllerException {
        try {
            Car man = carService.getById( carRequestNoRent.getId());
            carService.updateCarById(
                    carRequestNoRent.getId(),
                    carRequestNoRent.getName(),
                    carRequestNoRent.getDescription(),
                    carRequestNoRent.getCost()
            );
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
    @DeleteMapping("/user/deleteCarByNameU")
    public ResponseEntity<?> deleteCarByNameU(@RequestBody NameRequest nameRequest)throws ControllerException {

        try {
            Car man = carService.getByName(nameRequest.getName());
            userRentFormRepository.deleteByUserName(nameRequest.getName());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException | RepositoryException e ) {
            throw new ControllerException(e);

        }


    }
    @GetMapping("/admin/getAllCarsForAdmin")
    public ResponseEntity<?> getAllCarsForAdmin() throws ControllerException{
        try {
            return new ResponseEntity<>(carService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {

            throw new ControllerException(e);

        }
    }
    @PostMapping("/admin/isCarExistByName")
    public ResponseEntity<?> isCarExistByName(@RequestBody NameRequest nameRequest) throws ControllerException{
        try {
            if(!carService.existsByName(nameRequest.getName())){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }

    }
    @GetMapping("/user/userGetCarByName/{name}")
    public ResponseEntity<?> userGetCarByName(@PathVariable(name = "name")String name)throws ControllerException {
        Car stuff = null;
        try {
            stuff = carService.getByName(name);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @GetMapping("admin/adminGetCarByName/{name}")
    public ResponseEntity<?> adminGetCarByName(@PathVariable(name = "name")String name) throws ParseException, ControllerException {
        Car stuff = null;
        try {
            stuff = carService.getByName(name);
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date = simpleDateFormat.format(stuff.getExpirationDate());
            System.out.println(date);
            stuff.setExpirationDate(simpleDateFormat.parse(date));
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("Error occured");
            throw new ControllerException(e);

        }

    }
    @GetMapping("/user/getAllCarsForUser")
    public ResponseEntity<?> getAllCarsForUser()throws ControllerException {
        try {
            return new ResponseEntity<>(carService.getAll(),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);

        }
    }
}
