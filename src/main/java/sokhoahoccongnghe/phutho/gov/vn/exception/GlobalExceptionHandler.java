package sokhoahoccongnghe.phutho.gov.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.BAD_REQUEST, null, false);
    }

    @ExceptionHandler(value = {NullPropertyException.class})
    public ResponseEntity<Object> handleNullValue(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.BAD_REQUEST, null, false);
    }
    @ExceptionHandler(value = {FileUploadException.class})
    public ResponseEntity<Object> handleFileUploadException(RuntimeException ex) {
        ex.printStackTrace();
        ex.getCause().printStackTrace();
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, false);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleGeneral(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseBaseModel.responseBuidler("unknown server error", HttpStatus.INTERNAL_SERVER_ERROR, null
                , false);
    }

}
