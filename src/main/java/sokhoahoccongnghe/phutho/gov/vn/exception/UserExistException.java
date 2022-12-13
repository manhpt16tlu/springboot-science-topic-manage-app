package sokhoahoccongnghe.phutho.gov.vn.exception;

public class UserExistException extends RuntimeException{
    public UserExistException(String mess){
        super(mess);
    }
}
