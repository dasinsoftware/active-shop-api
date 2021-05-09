package za.co.momentum.activeshopapi.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data @NoArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;

    ApiError(HttpStatus status) {
        this();
        this.status = status;
    }
}