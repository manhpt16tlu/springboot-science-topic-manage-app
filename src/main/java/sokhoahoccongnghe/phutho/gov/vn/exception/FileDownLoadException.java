package sokhoahoccongnghe.phutho.gov.vn.exception;

public class FileDownLoadException extends RuntimeException{
    public FileDownLoadException(String mess){
        super(mess);
    }
    public FileDownLoadException(String mess,Throwable cause){
        super(mess,cause);
    }
}
