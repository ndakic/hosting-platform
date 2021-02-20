package uns.ac.rs.hostplatserver.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.JoinColumn;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name can not be empty string")
	private String name;
	
	@NotBlank(message = "Description can not be empty string")
	private String description;
	
	private Date create_date;


	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "Project_User", 
        joinColumns = { @JoinColumn(name = "project_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
	private Set<User> users;
	
    
    
    private boolean private_project = true;
    private boolean active_project = true;
    
	public Project(Long id, String name, String description, Set<User> set, boolean private_project) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.users = set;
		this.private_project = private_project;
	}

	public Project(Long project_id) {
		this.id = project_id;
	}
	


	public Project(Long id, String persistedProjectName, String persistedProjectDescription) {
		this.id = id;
		this.name = persistedProjectName;
		this.description = persistedProjectDescription;
	}


}
