package sokhoahoccongnghe.phutho.gov.vn.service;

import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;

import java.util.Optional;

public interface UserService {
    UserDto getUserByUsername(String username);
    boolean checkExistByUsername(String username);
    UserDto createUser(UserDto userRequest);
}
