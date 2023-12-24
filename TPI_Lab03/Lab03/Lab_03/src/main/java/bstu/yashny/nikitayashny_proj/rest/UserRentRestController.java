package bstu.yashny.nikitayashny_proj.rest;

import bstu.yashny.nikitayashny_proj.dto.DateRequest;
import bstu.yashny.nikitayashny_proj.dto.IdRentRequest;
import bstu.yashny.nikitayashny_proj.dto.IdRequest;
import bstu.yashny.nikitayashny_proj.dto.UserRentRequestNoId;
import bstu.yashny.nikitayashny_proj.exception.ControllerException;
import bstu.yashny.nikitayashny_proj.exception.RepositoryException;
import bstu.yashny.nikitayashny_proj.exception.ServiceException;
import bstu.yashny.nikitayashny_proj.models.RentForm;
import bstu.yashny.nikitayashny_proj.repository.UserRentFormRepository;
import bstu.yashny.nikitayashny_proj.service.CarService;
import bstu.yashny.nikitayashny_proj.service.UserRentFormService;
import bstu.yashny.nikitayashny_proj.validator.RentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRentRestController {
    @Autowired
    private UserRentFormService userRentFormService;
    @Autowired
    private UserRentFormRepository rentFormRepository;
    @Autowired
    private CarService CarService;
    @Autowired
    private RentValidator rentValidator;

    @PostMapping("/admin/getAllByCarExpirationDateLessThan")
    public ResponseEntity<?> getAllByCarExpirationDateLessThan(@RequestBody DateRequest dateRequest) throws ControllerException {
        try {
            return new ResponseEntity<>(userRentFormService.getAllByCarExpirationDateLessThan(dateRequest.getDate()), HttpStatus.FOUND);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

    }
    @DeleteMapping("/admin/deleteByUserIdAndCarId")
    public ResponseEntity<?> deleteByUserIdAndCarId() {
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/admin/setUserRentFormById")
    public ResponseEntity<?> setUserRentFormById(@RequestBody IdRentRequest idRentRequest)throws ControllerException {
        try {
            RentForm man = userRentFormService.getById(idRentRequest.getId());
            userRentFormService.setUserRentFormById(idRentRequest.getId(),idRentRequest.isRent());
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }

    }
    @GetMapping("/user/getAllByUserId/{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable(name = "id")Long id) throws ControllerException{
        try {
            return new ResponseEntity<>(userRentFormService.getAllByUserId(id),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @GetMapping("/admin/getAllByRent/{data}")
    public ResponseEntity<?> getAllByRent(@PathVariable(name = "data")boolean data) throws ControllerException{

        try {
            return new ResponseEntity<>(userRentFormService.getAllByRent(data),HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PostMapping("/admin/isUserRentExistByCarId")
    public ResponseEntity<?> isUserRentExistByCarId(@RequestBody IdRequest idRequest) throws ControllerException{
        try {
            if(userRentFormService.existsByCarId(idRequest.getId())) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
    @PostMapping("/user/isUserRentExistByUserId")
    public ResponseEntity<?> isUserRentExistByUserId(@RequestBody IdRequest idRequest) throws ControllerException{
        try {
            if(!rentFormRepository.existsByUserId(idRequest.getId())){
                return  new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.FOUND);
        } catch (RepositoryException e) {
            throw new ControllerException(e);
        }
    }
    @PostMapping("/user/createUserRent")
    public ResponseEntity<?> createUserRent(@RequestBody @Validated UserRentRequestNoId userRentRequestNoId , BindingResult bindingResult)throws ControllerException {
        if(!bindingResult.hasErrors()) {
            RentForm userRentForm = new RentForm(
                    userRentRequestNoId.getUser(),
                    userRentRequestNoId.getName(),
                    userRentRequestNoId.getSurname(),
                    userRentRequestNoId.getCar()
            );
            try {
                RentForm test = new RentForm();
                test.setId(15L);
                rentValidator.validate(test,bindingResult);
                if(bindingResult.hasErrors()) throw new ControllerException("not correct data");
                userRentFormService.create(userRentForm);
                return new ResponseEntity<>(userRentForm, HttpStatus.OK);
            } catch (Exception e) {
                throw new ControllerException(e);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
