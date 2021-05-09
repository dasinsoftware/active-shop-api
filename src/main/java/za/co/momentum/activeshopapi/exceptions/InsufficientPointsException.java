package za.co.momentum.activeshopapi.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class InsufficientPointsException extends RuntimeException{
    private final String message = "Insufficient points available";
}
