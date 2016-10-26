package com.ccc.testtask.handler;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kirill Milinevskiy
 */
@ControllerAdvice
public class ValidationErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RepositoryConstraintViolationException.class})
    public ResponseEntity<ValidationErrorResponse> processValidationError(Exception ex, WebRequest request) {
        RepositoryConstraintViolationException newEx = (RepositoryConstraintViolationException) ex;

        ValidationErrorResponse response = new ValidationErrorResponse();
        response.setErrorMessages(newEx.getErrors().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private class ValidationErrorResponse {
        private List<String> errorMessages;

        public List<String> getErrorMessages() {
            return errorMessages;
        }

        public ValidationErrorResponse setErrorMessages(List<String> errorMessages) {
            this.errorMessages = errorMessages;
            return this;
        }
    }
}
