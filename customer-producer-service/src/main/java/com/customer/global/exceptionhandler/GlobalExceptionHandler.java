package com.customer.global.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.customer.exception.EncryptionException;
import com.customer.exception.GeneralException;
import com.customer.exception.InvalidDataException;
import com.customer.model.RestAPIResponse;
import com.customer.util.JsonUtil;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String ERROR = "error";

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    List<String> errors =
        ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());

    return ResponseEntity.status(status)
        .headers(headers)
        .body(
            new RestAPIResponse()
                .status(ERROR)
                .message(JsonUtil.objToString(errors))
                .errorType(InvalidDataException.class.getName()));
  }

  @ExceptionHandler(EncryptionException.class)
  public ResponseEntity<RestAPIResponse> error(EncryptionException ex) {

    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body(
            new RestAPIResponse()
                .status(ERROR)
                .message(ex.getMessage())
                .errorType(EncryptionException.class.getName()));
  }

  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<RestAPIResponse> error(GeneralException ex) {

    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body(
            new RestAPIResponse()
                .status(ERROR)
                .message(ex.getMessage())
                .errorType(GeneralException.class.getName()));
  }
}
