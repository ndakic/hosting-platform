package uns.ac.rs.hostplatserver.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.dto.UserEditDTO;
import uns.ac.rs.hostplatserver.dto.UserRegistrationDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
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
    
    @GetMapping("/public/verify-account/{token}")
    public ResponseEntity verifyUserAccount(@PathVariable String token) {
        userService.activateAccount(token);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<UserDTO> getProject(@PathVariable("id") Long id) throws ResourceNotFoundException {
      	User user = userService.findOne(id);
  		return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
  	}
    

    @GetMapping(value = "/getUserForProject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Set<UserDTO>> getUserForProject(@PathVariable("id") Long id) throws ResourceNotFoundException {
      	Set<User> users = userService.getUserForProject(id);
      	Set<UserDTO> usersDTO = new HashSet<UserDTO>();
		for (User user: users) {
			usersDTO.add(UserMapper.toDTO(user));
		}
  		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
  	}
    
    @PostMapping(value = "/change-avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity changeProfileImage(@RequestParam("file") MultipartFile file) {
        User user = userService.changeAvatar(file);
        return new ResponseEntity<>(UserMapper.toDTO(user), HttpStatus.OK);
    }
    


}