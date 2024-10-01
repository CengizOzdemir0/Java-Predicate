package cengiz.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<String> handleKurumNotFound(BaseException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return new ResponseEntity<>("Beklenmeyen hata oluştu: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}