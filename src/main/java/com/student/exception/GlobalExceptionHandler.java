package com.student.exception;

import com.student.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //handle specific exceptions

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleRequestNotFoundException(RequestNotFoundException exception,
                                                                       WebRequest webRequest
    ){
            ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),
                                                 webRequest.getDescription(false ));

            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleBlogApiException(BlogApiException exception,
                                               WebRequest webRequest){

        ErrorDetails errorDetails=new ErrorDetails(new Date(),exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);


    }

    //handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){

        ErrorDetails errorDetails= new ErrorDetails(new Date(),exception.getMessage(),
                webRequest.getDescription(false ));
        return  new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
