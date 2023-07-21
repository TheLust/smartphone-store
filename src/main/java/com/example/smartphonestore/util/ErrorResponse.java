package com.example.smartphonestore.util;

import com.example.smartphonestore.exceptions.AlreadyExistsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorResponse {

    public static void returnErrors(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();

        for (FieldError error : errors){
            errorMsg.append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append("\n");
        }
        throw new AlreadyExistsException(errorMsg.toString());
    }
}
