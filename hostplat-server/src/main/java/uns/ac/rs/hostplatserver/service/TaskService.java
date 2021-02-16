package uns.ac.rs.hostplatserver.service;

import java.util.List;
import java.util.Set;

import uns.ac.rs.hostplatserver.dto.UserTaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.model.User;

public interface TaskService {
	
	public Task findOne(Long id) throws ResourceNotFoundException;
	
	public List<Task> findAll();

	public Task create(Task task) throws Exception;

	public Task update(Task task) throws Exception;

	public void delete(Long id);

	public Task closeTask(Long id);

	public List<Task> findAllCloseTasks();

	public List<Task> findAllOpenTasks();

	public List<Task> findAllByProjectId(List<Task> tasks, Long id);


	public List<Task> findAllForMilestone(List<Task> tasks, Long id); 
	
	public Milestone setMilestoneToTask(Long task_id,Milestone milestone);

	public Set<User> setUsersToTask(Long task_id, Set<User> users); 
	

}
