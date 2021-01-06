package uns.ac.rs.hostplatserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping(path = "/all")
    public ResponseEntity<String> helloWorldAll() {
        return new ResponseEntity<String>("Hello World all", HttpStatus.OK);
    }

    @GetMapping(path = "/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> helloWorldUser() {
        return new ResponseEntity<String>("Hello world user", HttpStatus.OK);
    }



}