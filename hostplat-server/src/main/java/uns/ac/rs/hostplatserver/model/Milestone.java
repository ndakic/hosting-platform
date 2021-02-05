package uns.ac.rs.hostplatserver.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Future;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "milestone")
public class Milestone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title can not be empty string")
	private String title;
	
	@Future(message="Start date must be in future")
	private Date start_date;

	@Future(message="End date must be in future")
	private Date end_date;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
    private StatusEntity status;
    
    public Milestone(Long id, String title, Date start_date, Date end_date, User user) {
		this.id = id;
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.user = user;
	}


}
