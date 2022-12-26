package sokhoahoccongnghe.phutho.gov.vn.security.usersecure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sokhoahoccongnghe.phutho.gov.vn.entity.User;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(
                "can not find user with username "+username
        ));
//        if(isDisabledUser(user)) throw new DisabledException("user disabled");
        return UserDetailsImpl.build(user);
    }
    private boolean isDisabledUser(User user){
        return user.isDisabled();
    }
}
