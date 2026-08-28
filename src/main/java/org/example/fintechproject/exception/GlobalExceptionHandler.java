package org.example.fintechproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handles TaskNotFoundException  "task not found"
    @ExceptionHandler(TaskNotFoundException .class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException (TaskNotFoundException  ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    // handles failure in @Valid checks on fields
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldErrors().stream().map(err -> err.getField()+" "
        + err.getDefaultMessage()).findFirst().orElse("Validation Failed!");
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
