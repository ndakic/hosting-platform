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
public class TaskDTO {
	
	private Long id;
	private String title;
	private String description;
	private Date create_date;
	private Date end_date;
	private Long author_id;
	private Set<UserDTO> assigned_users;
	private Long project_id;
	private Long milestone_id;
	private Set<LabelDTO> labels;

}
