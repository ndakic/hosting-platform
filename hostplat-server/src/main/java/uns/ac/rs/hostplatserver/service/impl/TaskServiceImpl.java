package uns.ac.rs.hostplatserver.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.LabelEntity;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.repository.TaskRepository;
import uns.ac.rs.hostplatserver.service.LabelService;
import uns.ac.rs.hostplatserver.service.TaskService;
import uns.ac.rs.hostplatserver.service.UserService;
import uns.ac.rs.hostplatserver.util.DateUtil;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
    private TaskRepository taskRepository;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private LabelService labelService;

	@Override
	public Task findOne(Long id) throws ResourceNotFoundException {
		
		return this.taskRepository.findById(id)
	        		.orElseThrow(
	                ()-> new ResourceNotFoundException(String.format("Task with id %s not found!", id))
	       );
	}
	
	@Override
	public List<Task> findAll() {
		return this.taskRepository.findAll();

	}

	@Override
	public Task create(Task task) throws Exception {
		task.setCreate_date(DateUtil.nowSystemTime());
		Set<User> users = new HashSet<>();

		for (User user : task.getAssigned_users()) {
			User u = userService.findOne(user.getId());
			users.add(u);
		}
		
		task.setAssigned_users(users);
		
		Set<LabelEntity> labels = new HashSet<>();

		for (LabelEntity label : task.getLabels()) {
			LabelEntity l = labelService.findOne(label.getId());
			labels.add(l);
		}
		
		task.setAssigned_users(users);
		task.setLabels(labels);
		
        return this.taskRepository.save(task);
       

	}

	@Override
	public Task update(Task task) throws Exception {
		Task taskToUpdate = this.findOne(task.getId());
		if(task.getTitle()!=null) {
			taskToUpdate.setTitle(task.getTitle());
		}
		if(task.getDescription()!= null) {
			taskToUpdate.setDescription(task.getDescription());
		}
		if(task.getEnd_date()!=null) {
			taskToUpdate.setEnd_date(task.getEnd_date());
		}
		if(task.getProject()!= null) {
			taskToUpdate.setProject(task.getProject());
		}
		if(task.getMilestone()!= null) {
			taskToUpdate.setMilestone(task.getMilestone());
		}
		Set<User> users = new HashSet<>();

		for (User user : task.getAssigned_users()) {
			User u = userService.findOne(user.getId());
			users.add(u);
		}
				
		taskToUpdate.setAssigned_users(users);
		
		Set<LabelEntity> labels = new HashSet<>();

		for (LabelEntity label : task.getLabels()) {
			LabelEntity l = labelService.findOne(label.getId());
			labels.add(l);
		}
				
		taskToUpdate.setLabels(labels);
		
		
		return this.taskRepository.save(taskToUpdate);
	}

	@Override
	public void delete(Long id) {
		Task task = findOne(id);
		task.setAssigned_users(new HashSet<>());
		task.setLabels(new HashSet<>());
		taskRepository.save(task);
		this.taskRepository.deleteById(id);
		
	}

	@Override
	public Task closeTask(Long id) {
		Task task = findOne(id);
		task.setEnd_date(DateUtil.nowSystemTime());
		taskRepository.save(task);
		return task;
		
	}

	@Override
	public List<Task> findAllCloseTasks() {
		List<Task> allClose = new ArrayList<>();
		
		for (Task task : taskRepository.findAll()) {
			if(task.getEnd_date()!=null) {
				allClose.add(task);
			}
		}
		
		
		return allClose;

	}
	
	@Override
	public List<Task> findAllOpenTasks() {
		List<Task> allClose = new ArrayList<>();
		
		for (Task task : taskRepository.findAll()) {
			if(task.getEnd_date()==null) {
				allClose.add(task);
			}
		}
		
		
		return allClose;

	}

	@Override
	public void deleteByProjectId(Long id) {
		Task task = taskRepository.deleteByProjectId(id);
		task.setEnd_date(DateUtil.nowSystemTime());
		taskRepository.save(task);
		this.taskRepository.deleteById(id);
		
	}

}
