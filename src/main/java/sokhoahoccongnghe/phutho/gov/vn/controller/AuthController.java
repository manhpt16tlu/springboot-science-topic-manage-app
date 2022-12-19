package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.RoleDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.req.LoginRequest;
import sokhoahoccongnghe.phutho.gov.vn.dto.req.SignUpRequest;
import sokhoahoccongnghe.phutho.gov.vn.dto.res.JwtTokenResponse;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.exception.UserExistException;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;
import sokhoahoccongnghe.phutho.gov.vn.security.jwt.JwtProvider;
import sokhoahoccongnghe.phutho.gov.vn.security.usersecure.UserDetailsImpl;
import sokhoahoccongnghe.phutho.gov.vn.service.RoleService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/api/auth"})
@CrossOrigin(origins = "http://localhost:3000")
//controller k cần xác thực
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping(value = "/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.checkExistByUsername(signUpRequest.getUsername()))
            throw new UserExistException("username already taken");

        UserDto newUser = new UserDto();

        newUser.setUsername(signUpRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        newUser.setCreateDate(new Date());
        newUser.setOrgan(signUpRequest.getOrgan());
        newUser.setRank(signUpRequest.getRank());
        newUser.setName(signUpRequest.getName());

        Set<String> reqRoles = signUpRequest.getRoles();
        Set<RoleDto> userRoles = reqRoles.stream().map(roleString-> roleService.getRoleByName(RoleNameEnum.valueOf(roleString)).orElseThrow(
                    ()->new NotFoundException("role" + roleString)
            )
        ).collect(Collectors.toSet());

        newUser.setRoles(userRoles);

        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                userService.createUser(newUser),
                true);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String[] jwt = jwtProvider.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JwtTokenResponse response = new JwtTokenResponse();
        response.setRoles(roles);
        response.setUsername(userDetails.getUsername());
        response.setAccessToken(jwt[0]);
        response.setExpirationTime(new Date(Long.parseLong(jwt[1])));
        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                response,
                true);
    }
}
