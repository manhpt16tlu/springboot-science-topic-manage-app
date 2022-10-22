package sokhoahoccongnghe.phutho.gov.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sokhoahoccongnghe.phutho.gov.vn.model.ExceptionResponseModel;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ExceptionResponseModel> topicServiceHandle(Exception ex){
        ExceptionResponseModel res = ExceptionResponseModel
                .builder()
                .message(ex.getMessage())
                .time(new Date())
                .build();
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {NullPropertyException.class})
    public ResponseEntity<ExceptionResponseModel> handleNullValue(Exception ex) {
        ExceptionResponseModel res = ExceptionResponseModel
                .builder()
                .message(ex.getMessage())
                .time(new Date())
                .build();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

}
