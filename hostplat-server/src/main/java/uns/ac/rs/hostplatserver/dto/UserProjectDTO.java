package uns.ac.rs.hostplatserver.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProjectDTO {
	public Long project_id;
	public Set<UserDTO> users;

}