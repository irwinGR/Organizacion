package com.smart.prueba.entrypoint.validation;

import com.smart.prueba.entrypoint.response.ErrorResponse;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    public static ErrorResponse fromBindingErrors(Errors errors) {

        List messagges = new ArrayList();
        for (ObjectError objectError : errors.getAllErrors()) {
            System.out.println(objectError.getObjectName());
            messagges.add(objectError.getDefaultMessage());
        }

        ErrorResponse error = new ErrorResponse("Validation-Error ", "400", messagges.toString());

        return error;
    }
}
