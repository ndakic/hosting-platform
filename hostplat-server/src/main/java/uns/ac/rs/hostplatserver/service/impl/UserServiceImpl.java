package uns.ac.rs.hostplatserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import uns.ac.rs.hostplatserver.common.TimeProvider;
import uns.ac.rs.hostplatserver.constant.UserRoles;
import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.dto.UserEditDTO;
import uns.ac.rs.hostplatserver.dto.UserRegistrationDTO;
import uns.ac.rs.hostplatserver.exception.BadRequestException;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.mapper.UserMapper;
import uns.ac.rs.hostplatserver.model.ConfirmationToken;
import uns.ac.rs.hostplatserver.model.Project;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.repository.AuthorityRepository;
import uns.ac.rs.hostplatserver.repository.ConfirmationTokenRepository;
import uns.ac.rs.hostplatserver.repository.UserRepository;
import uns.ac.rs.hostplatserver.service.ProjectService;
import uns.ac.rs.hostplatserver.service.UserService;
import java.io.IOException;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private ProjectService projectService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private ConfirmationTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TimeProvider timeProvider;

    @Autowired
    private MailSenderService mailSenderService;
    
    @Autowired
    private Cloudinary cloudinary;


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
    
    @Override
    public User addUser(UserRegistrationDTO userInfo) {
        if (userRepository.findByUsername(userInfo.getUsername()) != null) {
            throw new BadRequestException("Username '" + userInfo.getUsername() + "' already exists.");
        }

        if (!userInfo.getPassword().equals(userInfo.getRepeatPassword())) {
            throw new BadRequestException("Provided passwords must be the same.");
        }

        if (userRepository.findByEmail(userInfo.getEmail()) != null) {
            throw new BadRequestException("Email '" + userInfo.getEmail() + "' is taken.");
        }

        User user = createNewUserObject(userInfo);
        userRepository.save(user);

        ConfirmationToken token = new ConfirmationToken(user);
        tokenRepository.save(token);

        mailSenderService.sendRegistrationMail(token);

        return user;
    }

    private User createNewUserObject(UserRegistrationDTO userInfo) {
        User user = UserMapper.toEntity(userInfo);
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        user.setImagePath("https://res.cloudinary.com/djxkexzcr/image/upload/v1574108111/zbvvptxlxzzhzomjvp2s.jpg");
        user.setLastPasswordResetDate(timeProvider.nowTimestamp());
        user.getUserAuthorities().add(authorityRepository.findByName(UserRoles.ROLE_USER));

        return user;
    }
    
    @Override
    public boolean activateAccount(String token) {
        ConfirmationToken confirmationToken = tokenRepository.findByToken(token);

        if (confirmationToken == null) {
            throw new ResourceNotFoundException("Confirmation token doesn't exist.");
        }

        if (confirmationToken.isUsed()) {
            throw new BadRequestException("This token has been already used.");
        }

        User user = confirmationToken.getUser();
        long timeDifference = timeProvider.timeDifferenceInMinutes(timeProvider.now(), confirmationToken.getDatetimeCreated());

        if (timeDifference < 30) {
            user.setActivatedAccount(true);
            userRepository.save(user);
            confirmationToken.setUsed(true);
            tokenRepository.save(confirmationToken);
            return true;
        } else {
            tokenRepository.delete(confirmationToken);
            userRepository.delete(user);
            throw new BadRequestException("Confirmation token timed out.");
        }

    }

	@Override
	public Set<User> getUserForProject(Long id) {
		Project project = projectService.findOne(id);
		return project.getUsers();
	}
	
	@Override
	public List<User> getAll() {
		return this.userRepository.findAll();
	}
	

    
    public User changeAvatar(MultipartFile file) {
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imagePath = (String) result.get("url");
            user.setImagePath(imagePath);
            userRepository.save(user);
        } catch (IOException e) {
            throw new BadRequestException("There was an error while uploading a profile image");
        }
        
        return user;
        
    }
}
