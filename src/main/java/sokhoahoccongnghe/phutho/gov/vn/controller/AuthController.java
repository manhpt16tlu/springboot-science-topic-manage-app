package sokhoahoccongnghe.phutho.gov.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sokhoahoccongnghe.phutho.gov.vn.dto.RoleDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.UserDto;
import sokhoahoccongnghe.phutho.gov.vn.dto.req.SignUpRequest;
import sokhoahoccongnghe.phutho.gov.vn.exception.NotFoundException;
import sokhoahoccongnghe.phutho.gov.vn.exception.UserExistException;
import sokhoahoccongnghe.phutho.gov.vn.model.MessageEnum;
import sokhoahoccongnghe.phutho.gov.vn.model.ResponseBaseModel;
import sokhoahoccongnghe.phutho.gov.vn.model.RoleNameEnum;
import sokhoahoccongnghe.phutho.gov.vn.security.jwt.JwtProvider;
import sokhoahoccongnghe.phutho.gov.vn.service.RankService;
import sokhoahoccongnghe.phutho.gov.vn.service.RoleService;
import sokhoahoccongnghe.phutho.gov.vn.service.UserService;

import javax.validation.Valid;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/api/auth"})
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private RankService rankService;

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
        Set<RoleDto> userRoles = reqRoles.stream().map(roleString->{
            RoleDto roleDto = roleService.getRoleByName(RoleNameEnum.valueOf(roleString)).orElseThrow(
                    ()->new NotFoundException("role" + roleString)
            );
            return roleDto;
        }).collect(Collectors.toSet());

        newUser.setRoles(userRoles);

        return ResponseBaseModel.responseBuidler(MessageEnum.REQUEST_SUCCESS.getValue(), HttpStatus.OK,
                userService.createUser(newUser),
                true);
    }
}
