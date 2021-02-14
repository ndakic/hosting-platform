package uns.ac.rs.hostplatserver.service;

import java.util.List;
import java.util.Set;

import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Project;
import uns.ac.rs.hostplatserver.model.User;

public interface ProjectService {
	
	public Project findOne(Long id) throws ResourceNotFoundException;
	
	public List<Project> findAll();

	public Project create(Project project) throws Exception;

	public Project update(Project project) throws Exception;

	public void delete(Long id);

	public List<Project> findAllForUser(Long id);

	public List<Project> findAllPublic();

	public Set<User> findAllUsersForProject(Long id);

	public List<Milestone> findAllMilestonesForProject(Long id);

	public List<Milestone> findAllMilestonesForProjectWithTask(Long id); 
	

}
