package sokhoahoccongnghe.phutho.gov.vn.util;


import org.springframework.data.jpa.repository.JpaRepository;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;

public class GetEntityById{
    public static <T extends JpaRepository<S,N>,S,N> S getEntity(T repo,N id){
        return repo.findById(id).orElseThrow(()->
            new NotFoundException((Integer) id,"entity"));
    }
}
