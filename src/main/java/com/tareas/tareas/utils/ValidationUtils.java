package com.tareas.tareas.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidationUtils {

  public static List<String> getValidationErrors(BindingResult result) {
    return result.getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());
  }

  public static ResponseEntity<List<String>> createValidationErrorResponse(BindingResult result) {
    List<String> errors = getValidationErrors(result);
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
