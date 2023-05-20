package form.api.com.infra.exception;

import form.api.com.infra.exception.errors.CustomErrorException;
import form.api.com.infra.exception.errors.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ErrorException {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> Erro404(EntityNotFoundException ex) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);

        String stackTrace = stringWriter.toString();

        return new ResponseEntity<>(  new ErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                stackTrace,
                ex.getCause()),
                HttpStatus.NOT_FOUND
        );


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> ValidateErro400(MethodArgumentNotValidException ex) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);

        String stackTrace = stringWriter.toString();
        return new ResponseEntity<>(  new ErrorResponse(
                status,
                ex.getFieldError().getDefaultMessage(),
                stackTrace,
                ex.getFieldError().getField()),
                status
        );
    }

    @ExceptionHandler(CustomErrorException.class)
    public ResponseEntity<ErrorResponse> handleCustomErrorExceptions(Exception e) {


        CustomErrorException customErrorException = (CustomErrorException) e;
        HttpStatus status = customErrorException.getStatus();


        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        customErrorException.printStackTrace(printWriter);

        String stackTrace = stringWriter.toString();
        return new ResponseEntity<>(  new ErrorResponse(
                status,
                customErrorException.getMessage(),
                stackTrace,
                customErrorException.getData()),
                status
        );

    }

}
