package com.raman.spring.validators;


import com.raman.spring.dao.PersonDAO;
import com.raman.spring.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.raman.spring.entity.Person;
@Component
public class PersonValidator implements Validator {

    @Autowired
    private PersonService personService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        System.out.println("validate");
        if(personService.getPerson(person.getFullName()) != null){
            errors.rejectValue("fullName", "", "This name is already taken");
        }
    }
}
