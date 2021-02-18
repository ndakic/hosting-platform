package uns.ac.rs.hostplatserver.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticBackDTO {
	
	public List<TaskDTO> open;
	public List<TaskDTO> close;


}
