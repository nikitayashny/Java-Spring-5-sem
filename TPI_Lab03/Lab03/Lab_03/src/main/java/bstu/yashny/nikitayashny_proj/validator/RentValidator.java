package bstu.yashny.nikitayashny_proj.validator;

import bstu.yashny.nikitayashny_proj.models.RentForm;
import bstu.yashny.nikitayashny_proj.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RentForm car =(RentForm)o;
        if(car.getId()<0){
            errors.rejectValue("id","negative value");
        }
    }
}
