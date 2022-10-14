package sokhoahoccongnghe.phutho.gov.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sokhoahoccongnghe.phutho.gov.vn.model.ExceptionResponseModel;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TopicServiceException.class)
    public ResponseEntity<ExceptionResponseModel> topicServiceHandle(TopicServiceException ex){
        ExceptionResponseModel res = ExceptionResponseModel
                .builder()
                .mess(ex.getMessage())
                .time(new Date())
                .build();
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
