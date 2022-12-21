package sokhoahoccongnghe.phutho.gov.vn.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super("not found record in database");
    }
    public NotFoundException(String entity){
        super("can not find " + entity+ " entity");
    }
    public NotFoundException(Integer id, String entity) {
        super("can not find " + entity + " with id " + id);
    }
}
