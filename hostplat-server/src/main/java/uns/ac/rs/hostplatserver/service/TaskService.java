package uns.ac.rs.hostplatserver.service;

import java.util.List;

import uns.ac.rs.hostplatserver.dto.UserTaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Task;

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

	public void setUserToTask(UserTaskDTO userTask);

	public List<Task> findAllForMilestone(List<Task> tasks, Long id); 
	

}
