package uns.ac.rs.hostplatserver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.dto.UserEditDTO;
import uns.ac.rs.hostplatserver.dto.UserRegistrationDTO;
import uns.ac.rs.hostplatserver.mapper.UserMapper;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public ResponseEntity<String> helloWorldAll() {
        return new ResponseEntity<String>("Hello World all", HttpStatus.OK);
    }

    @GetMapping(path = "/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> helloWorldUser() {
        return new ResponseEntity<String>("Hello world user", HttpStatus.OK);
    }
    
    @GetMapping("/my-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> getMyProfileData() {
        User user = userService.getMyProfileData();
        return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
    }
    
    @PutMapping("/my-profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> editMyProfile(@Valid @RequestBody UserEditDTO userInfo) {
        User user = userService.editUser(userInfo);
        return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
    }
    
    @PostMapping("/public/add-user")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserRegistrationDTO userInfo) {
        User user = userService.addUser(userInfo);
        return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
    }



}