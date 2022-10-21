package sokhoahoccongnghe.phutho.gov.vn.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer id, String entity) {
        super("Can not find " + entity + " with id " + id);
    }
}
