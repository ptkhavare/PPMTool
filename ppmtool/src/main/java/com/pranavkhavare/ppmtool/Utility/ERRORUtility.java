package com.pranavkhavare.ppmtool.Utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains static methods to handle errors.
 */
public class ERRORUtility {

    /**
     * Identifies the error fields and cause of the error.
     * @param result BindingResult object
     * @return Map with error fields and what caused the error.
     */
    public static ResponseEntity<Map<String, String>> FieldErrorMap(BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            List<FieldError> errorList = result.getFieldErrors();
            for (FieldError error : errorList) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
