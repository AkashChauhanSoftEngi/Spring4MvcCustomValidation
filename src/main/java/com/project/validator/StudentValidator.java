package com.project.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.model.Student;


@Component
public class StudentValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {

        Student student = (Student)target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","id.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","id.lastname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"sex","id.sex");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","id.email");
        String email = student.getEmail();
       if(email !=null) {
            if (!student.getEmail().startsWith("ABC")) {
                errors.rejectValue("email", "email.valid");
            }
       }
    }
}
