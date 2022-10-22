package sokhoahoccongnghe.phutho.gov.vn.exception;

public class NullPropertyException extends RuntimeException {
    public NullPropertyException(){
        super("some property can not be NULL");
    }
    public NullPropertyException(String prop){
        super(prop + " can not be NULL");
    }
}
