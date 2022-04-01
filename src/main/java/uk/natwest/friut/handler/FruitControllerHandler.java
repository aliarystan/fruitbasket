package uk.natwest.friut.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.natwest.friut.exception.FruitNotFoundException;

@ControllerAdvice
public class FruitControllerHandler {
    @ExceptionHandler(FruitNotFoundException.class)
    public ResponseEntity<FruitExceptionResponse> fruitNotFoundException(FruitNotFoundException ex) {
        return new ResponseEntity<>(new FruitExceptionResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
