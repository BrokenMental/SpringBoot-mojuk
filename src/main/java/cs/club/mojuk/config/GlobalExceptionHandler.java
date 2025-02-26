package cs.club.mojuk.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //404
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Not Found");
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", ex.getReason());
        errorResponse.put("path", ex.getHeaders().getFirst("X-Request-Path"));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //boot 3.x 이상에서 작동하지 않을 수 있음
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Not Found");
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", "The requested resource was not found");
        errorResponse.put("path", ex.getRequestURL());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
