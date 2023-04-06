package form.api.com.infra.exception;

import form.api.com.infra.exception.errors.CustomErrorException;
import form.api.com.infra.exception.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class ErrorException {


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
