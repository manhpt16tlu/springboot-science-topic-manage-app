package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;

import java.util.Optional;

public interface UserService {
    UserDto getUserByUsername(String username);
    boolean checkExistByUsername(String username);
    UserDto createUser(UserDto userRequest);
    Page<UserDto> getAll(int page, int size,String organ,String username);
    void disableUser(Integer userId);
}
