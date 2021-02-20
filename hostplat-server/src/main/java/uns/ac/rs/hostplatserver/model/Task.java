package uns.ac.rs.hostplatserver.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title can not be empty string")
	private String title;
	
	@NotBlank(message = "Description can not be empty string")
	private String description;
	
	private Date create_date;

	private Date end_date;
	
	private boolean active_task = true;

	@ManyToOne
	@JoinColumn(name="author_id", referencedColumnName="id")
	private User author;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "Task_User", 
        joinColumns = { @JoinColumn(name = "task_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
	private Set<User> assigned_users;
	
	@ManyToOne
	@JoinColumn(name="project_id", referencedColumnName="id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="milestone_id", referencedColumnName="id")
	private Milestone milestone;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "Task_Label", 
        joinColumns = { @JoinColumn(name = "task_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "label_id") }
    )
	private Set<LabelEntity> labels;
	
	// dodati file i commit
	
	
	public Task(Long id, String title, String description, Date create_date, Date end_date, User user,
			Set<User> collect, Project project, Milestone milestone, Set<LabelEntity> collect2) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.create_date = create_date;
		this.end_date = end_date;
		this.author = user;
		this.assigned_users = collect;
		this.project = project;
		this.milestone = milestone;
		this.labels = collect2;
	}

}
