package sokhoahoccongnghe.phutho.gov.vn.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;

public interface UserService {
    UserDto getUserByUsername(String username);
    boolean checkExistByUsername(String username);
    UserDto createUser(UserDto userRequest);
    Page<UserDto> getAll(int page, int size,String organ,String username);
    void disableUser(Integer userId);
    void updateInformation(Integer userId,UserDto userRequest);
    void changePassword(String newPass,String oldPass);
    UserDetails getPrincipal();
}
