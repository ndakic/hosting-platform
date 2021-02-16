package uns.ac.rs.hostplatserver.service;

import java.util.List;
import java.util.Set;

import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.dto.UserEditDTO;
import uns.ac.rs.hostplatserver.dto.UserRegistrationDTO;
import uns.ac.rs.hostplatserver.exception.BadRequestException;
import uns.ac.rs.hostplatserver.model.User;

public interface UserService {

    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
	User findOne(Long id) throws BadRequestException;
	User getMyProfileData();
	User editUser(UserEditDTO userInfo);
	User addUser(UserRegistrationDTO userInfo);
	boolean activateAccount(String token);
	Set<User> getUserForProject(Long id);
	List<User> getAll();
}