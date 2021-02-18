package uns.ac.rs.hostplatserver.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneDTO {
	
	private Long id;
	private String title;
	private Date start_date;
	private Date end_date;
	private Long user_id;
	private boolean close;

}
