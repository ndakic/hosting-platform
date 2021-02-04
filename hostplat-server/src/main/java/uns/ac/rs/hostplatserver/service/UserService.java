package uns.ac.rs.hostplatserver.service;




import java.util.List;

import uns.ac.rs.hostplatserver.dto.UserDTO;

public interface UserService {

    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
}