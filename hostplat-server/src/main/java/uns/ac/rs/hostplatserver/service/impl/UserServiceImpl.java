package uns.ac.rs.hostplatserver.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.dto.UserEditDTO;
import uns.ac.rs.hostplatserver.exception.BadRequestException;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.repository.UserRepository;
import uns.ac.rs.hostplatserver.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findById(Long id) throws BadRequestException {
        try {
            User user = userRepository.findById(id).get();
            return new UserDTO(user);
        } catch (NoSuchElementException e) {
            throw new BadRequestException("User with id '" + id + "' doesn't exist.");
        }
    }

    @Override
    public UserDTO findByUsername(String username) throws BadRequestException {
        try {
            User user = userRepository.findByUsername(username);
            return new UserDTO(user);
        } catch (UsernameNotFoundException e) {
            throw new BadRequestException("User with username '" + username + "' doesn't exist.");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user)).collect(Collectors.toList());
    }
    
    @Override
    public User findOne(Long id) throws BadRequestException {
    	return this.userRepository.findById(id)
        		.orElseThrow(
                ()-> new ResourceNotFoundException(String.format("User with id %s not found!", id))
       );
    }
    
    @Override
    public User getMyProfileData() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    @Override
    public User editUser(UserEditDTO userInfo) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        User dbUser = userRepository.findByEmail(userInfo.getEmail());

        if(dbUser != null) {
            if (dbUser.getId() != user.getId()) {
                throw new BadRequestException("Email '" + userInfo.getEmail() + "' is taken.");
            }
        }

        user.setEmail(userInfo.getEmail());

        userRepository.save(user);

        return user;
    }
}
