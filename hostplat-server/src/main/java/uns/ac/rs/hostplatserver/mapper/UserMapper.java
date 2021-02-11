package uns.ac.rs.hostplatserver.mapper;

import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.dto.UserRegistrationDTO;
import uns.ac.rs.hostplatserver.model.User;

public class UserMapper {
	
	public static UserDTO toDTO(User user) {
		return new UserDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
	}
	
	public static User toUser(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
	}
	
    public static User toEntity(UserRegistrationDTO userInfo) {
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setActivatedAccount(false);
        user.setEmail(userInfo.getEmail());
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setPassword("");
        user.setImagePath("");

        return user;
    }

}
