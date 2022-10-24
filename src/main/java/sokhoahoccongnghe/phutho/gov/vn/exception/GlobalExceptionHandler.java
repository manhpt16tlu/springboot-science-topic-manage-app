package sokhoahoccongnghe.phutho.gov.vn.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(Exception ex) {
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.NOT_FOUND, new Date());
    }

    @ExceptionHandler(value = {NullPropertyException.class})
    public ResponseEntity<Object> handleNullValue(Exception ex) {
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.BAD_REQUEST, new Date());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> hanldeGeneral(Exception ex) {
        return ResponseBaseModel.responseBuidler("unknown server error", HttpStatus.INTERNAL_SERVER_ERROR, new Date());
    }

}
