package sit.int204.previousexam.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleFileNotFound(Exception ex, WebRequest request){
        ErrorResponses er = new ErrorResponses();
        er.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
        er.setMessage(ex.getMessage());
        er.setInstance(request.getDescription(false).substring(4));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponses> handleResourceNotFound(Exception ex, WebRequest request){
        ErrorResponses er = new ErrorResponses();
        er.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
        er.setMessage(ex.getMessage());
        er.setInstance(request.getDescription(false).substring(4));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponses handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request){
        BindingResult result = ex.getBindingResult();
        List<ErrorFiled> errorFileds = new ArrayList<>();

        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            ErrorFiled errorField = new ErrorFiled();
            errorField.setField(fieldError.getField());
            errorField.setMessage(fieldError.getDefaultMessage());
            errorFileds.add(errorField);
        }

        ErrorResponses errorResponses = new ErrorResponses();
        errorResponses.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponses.setMessage("Validation Error");
        errorResponses.setInstance(request.getRequestURI());
        errorResponses.setStackTrace(ex.getMessage());
        errorResponses.setErrors(errorFileds);
        return errorResponses;
//        List<ObjectError> globalErrors = result.getGlobalErrors();
//        for (ObjectError globalError : globalErrors) {
//            System.out.println(globalError.getDefaultMessage());
//            ErrorField errorField = new ErrorField();
//            errorField.setField(globalError.getCode().equals("CloseDateAfterPublishDate") ? "closeDate" : globalError.getCode());
//            errorField.setErrorMessage(globalError.getDefaultMessage());
//            errorFields.add(errorField);
//        }
    }


}
