package com.wearperfect.dataservice.api.exceptionHandler;

import com.wearperfect.dataservice.api.exception.UnAuthorizedException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<Object> handleDataAccessException(DataAccessException dataAccessException) {
        String errorText = String.format(
                "Exception occurred while performing database related operations. %s.",
                dataAccessException.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorText);
    }

    @ExceptionHandler({UnAuthorizedException.class})
    public ResponseEntity<Object> handleUnAuthorizedException(UnAuthorizedException unAuthorizedException) {
        return ResponseEntity.status(unAuthorizedException.getStatusCode()).body(unAuthorizedException.getMessage());
    }
}
