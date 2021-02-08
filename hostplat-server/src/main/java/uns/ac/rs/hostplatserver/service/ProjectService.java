package uns.ac.rs.hostplatserver.service;

import java.util.List;

import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Project;

public interface ProjectService {
	
	public Project findOne(Long id) throws ResourceNotFoundException;
	
	public List<Project> findAll();

	public Project create(Project project) throws Exception;

	public Project update(Project project) throws Exception;

	public void delete(Long id);

	public List<Project> findAllForUser(Long id);

	public List<Project> findAllPublic(); 
	

}
