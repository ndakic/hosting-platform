package uns.ac.rs.hostplatserver.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneTaskDTO {
	
	private Long task_id;
	private MilestoneDTO milestone;
	

}
