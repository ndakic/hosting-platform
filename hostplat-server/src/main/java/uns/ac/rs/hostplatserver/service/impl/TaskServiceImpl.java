package uns.ac.rs.hostplatserver.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.dto.UserTaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.LabelEntity;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.repository.StatusRepository;
import uns.ac.rs.hostplatserver.repository.TaskRepository;
import uns.ac.rs.hostplatserver.resource.LabelResource;
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
	
	@Autowired
	private StatusRepository statusRepository;

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
        task.setStatus(statusRepository.getOne((long) 20));

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
		
		return this.taskRepository.save(taskToUpdate);
	}

	@Override
	public void delete(Long id) {
		Task task = findOne(id);
		task.setAssigned_users(new HashSet<>());
		task.setLabels(new HashSet<>());
        task.setStatus(statusRepository.getOne((long) 21));
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
	public List<Task> findAllByProjectId(List<Task> tasks, Long id) {
		List<Task> returnTask = new ArrayList<>();
		
		for (Task task : tasks) {
			if(task.getProject().getId().equals(id)) {
				returnTask.add(task);
			}
		}
		return returnTask;
	}


	@Override
	public List<Task> findAllForMilestone(List<Task> tasks, Long id) {
			
		List<Task> returnTask = new ArrayList<>();
		
		for (Task task : tasks) {
			if(task.getMilestone().getId().equals(id)) {
				returnTask.add(task);
			}
		}
		return returnTask;
	}
	


	@Override
	public Set<User> setUsersToTask(Long task_id, Set<User> users) {
		Task task = this.findOne(task_id);
		List<User> allUsers = userService.getAll();
		Set<User> all = new HashSet<>();
		for(User us: allUsers) {
			for(User u: users) {
				if(u.getId().equals(us.getId())) {
					all.add(us);
				}
			}
		}
		task.setAssigned_users(all);
		taskRepository.save(task);
		return users;
	}

	@Override
	public Milestone setMilestoneToTask(Long task_id, Milestone milestone) {
		Task task = this.findOne(task_id);
		task.setMilestone(milestone);
		taskRepository.save(task);
		return milestone;
	}

	@Override
	public Set<LabelEntity> setLabelsToTask(Long task_id, Set<LabelEntity> labels) {
		Task task = this.findOne(task_id);
		Set<LabelEntity> a = task.getLabels();
		a.addAll(labels);		
		task.setLabels(a);
		taskRepository.save(task);
		return a;
	}


}
