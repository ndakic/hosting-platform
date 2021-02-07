package uns.ac.rs.hostplatserver.service;

import java.util.List;

import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Milestone;

public interface MilestoneService {
	
	public Milestone findOne(Long id) throws ResourceNotFoundException;
	
	public List<Milestone> findAll();

	public Milestone create(Milestone milestone) throws Exception;

	public Milestone update(Milestone milestone) throws Exception;

	public void delete(Long id); 
	

}
