package uns.ac.rs.hostplatserver.service;




import java.util.List;

import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.exception.BadRequestException;
import uns.ac.rs.hostplatserver.model.User;

public interface UserService {

    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
	User findOne(Long id) throws BadRequestException;
}