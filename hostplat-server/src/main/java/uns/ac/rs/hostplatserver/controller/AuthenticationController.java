package uns.ac.rs.hostplatserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uns.ac.rs.hostplatserver.dto.LoginDTO;
import uns.ac.rs.hostplatserver.dto.PasswordDTO;
import uns.ac.rs.hostplatserver.dto.TokenDTO;
import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.service.impl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        return new ResponseEntity<UserDTO>(userDetailsService.login(loginDTO), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshAuthenticationToken(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<TokenDTO>(userDetailsService.refreshAuthenticationToken(httpServletRequest), HttpStatus.OK);
    }

	
	@PostMapping("/change-password") 
	public ResponseEntity changePassword(@RequestBody @Valid PasswordDTO passwordDTO) {
	    userDetailsService.changePassword(passwordDTO.getOldPassword(),passwordDTO.getNewPassword()); 
	  	return ResponseEntity.ok().build(); 
	  	
	}
	 

}