package uns.ac.rs.hostplatserver.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
	
	private Long id;
	private String name;
	private String description;
	private Date create_date;
	private Set<UserDTO> users;
	private boolean private_project;


}
