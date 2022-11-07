package com.example.demo.validators;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeRepository empRepo;

//    public UniqueValidator(EmployeeRepository empRepo) {
//        this.empRepo =empRepo;
//    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        System.out.println("Entered validation method");

     Employee emp = empRepo.findByEmail(value);

        if(emp==null) {
            return true;
        } else {
            return false;
        }
    }
}
