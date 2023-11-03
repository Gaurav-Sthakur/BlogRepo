package com.myblog.blogApp.exception;

//import org.springframework.web.bind.annotation.ControllerAdvice;
import com.myblog.blogApp.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundHandler(ResourceNotFoundException exception, WebRequest webRequest){
     ApiResponse res=new ApiResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
     return new ResponseEntity<ApiResponse>(res, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ApiResponse> blogApiExceptionHandler(BlogApiException bgException,WebRequest webRequest){
        ApiResponse bGres=new ApiResponse(new Date(), bgException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ApiResponse>(bGres,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
     public ResponseEntity<ApiResponse> exceptionGlHandler(Exception ex,WebRequest wb){
        ApiResponse glException=new ApiResponse(new Date(), ex.getMessage(), wb.getDescription(false));
        return new ResponseEntity<ApiResponse>(glException,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
