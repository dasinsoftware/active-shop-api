package za.co.momentum.activeshopapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handleResourceNotFoundException(final ResourceNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler({ InsufficientPointsException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleInsufficientPoints(InsufficientPointsException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler({ javax.validation.ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage(ex.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }
}
