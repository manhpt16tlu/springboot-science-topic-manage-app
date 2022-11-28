package sokhoahoccongnghe.phutho.gov.vn.exception;

public class FileUploadException extends RuntimeException{

    public FileUploadException(String mess){
        super(mess);
    }
    public FileUploadException(String mess,Throwable cause){
        super(mess,cause);
    }
}
