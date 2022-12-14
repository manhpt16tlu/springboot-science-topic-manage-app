package sokhoahoccongnghe.phutho.gov.vn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.NOT_FOUND, null, false);
    }

    @ExceptionHandler(value = {NullPropertyException.class})
    public ResponseEntity<Object> handleNullValue(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.BAD_REQUEST, null, false);
    }


    @ExceptionHandler(value = {FileUploadException.class,FileDownLoadException.class,FileDeleteException.class})
    public ResponseEntity<Object> handleFileException(RuntimeException ex) {
        ex.printStackTrace();
        if(ex.getCause() != null)
            ex.getCause().printStackTrace();
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, false);
    }
    @ExceptionHandler(value = {UserExistException.class})
    public ResponseEntity<Object> handleSignUpException(RuntimeException ex) {
        ex.printStackTrace();
        if(ex.getCause() != null)
            ex.getCause().printStackTrace();
        return ResponseBaseModel.responseBuidler(ex.getMessage(), HttpStatus.BAD_REQUEST, null, false);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleGeneral(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseBaseModel.responseBuidler("server error", HttpStatus.INTERNAL_SERVER_ERROR, null
                , false);
    }
    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> handleAuthentication(AuthenticationException ex) {
        ex.printStackTrace();
        String mess;
        if(ex instanceof DisabledException)
            mess = MessageEnum.USER_DISABLED.getValue();
        else mess = MessageEnum.USER_UNAUTHORIZED.getValue();
        return ResponseBaseModel.responseBuidler(mess, HttpStatus.UNAUTHORIZED, null
                , false);
    }
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
        ex.printStackTrace();
        return ResponseBaseModel.responseBuidler("access denied", HttpStatus.FORBIDDEN, null
                , false);
    }

}
