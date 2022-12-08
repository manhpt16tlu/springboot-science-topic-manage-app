package sokhoahoccongnghe.phutho.gov.vn.exception;

public class FileDeleteException extends  RuntimeException{
    public FileDeleteException(String mess){
        super(mess);
    }
    public FileDeleteException(String mess,Throwable cause){
        super(mess,cause);
    }
}
