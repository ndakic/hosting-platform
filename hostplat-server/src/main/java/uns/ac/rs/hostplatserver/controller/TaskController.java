package uns.ac.rs.hostplatserver.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uns.ac.rs.hostplatserver.dto.LabelTaskDTO;
import uns.ac.rs.hostplatserver.dto.MilestoneDTO;
import uns.ac.rs.hostplatserver.dto.MilestoneTaskDTO;
import uns.ac.rs.hostplatserver.dto.TaskDTO;
import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.dto.UserTaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.mapper.MilestoneMapper;
import uns.ac.rs.hostplatserver.mapper.TaskMapper;
import uns.ac.rs.hostplatserver.mapper.UserMapper;
import uns.ac.rs.hostplatserver.model.LabelEntity;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.repository.LabelRepository;
import uns.ac.rs.hostplatserver.resource.LabelResource;
import uns.ac.rs.hostplatserver.service.LabelService;
import uns.ac.rs.hostplatserver.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private LabelRepository labelService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Task task = taskService.findOne(id);
		return new ResponseEntity<>(TaskMapper.toDTO(task), HttpStatus.OK);
	}

	
	@GetMapping(value = "/getAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getTasks() {
		List<Task> tasks = taskService.findAll();
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for (Task task: tasks) {
			tasksDTO.add(TaskMapper.toDTO(task));
		}
		return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) throws Exception {
		Task savedTask = taskService.create(TaskMapper.toTask2(taskDTO));

		return new ResponseEntity<>(TaskMapper.toDTO(savedTask), HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) throws Exception {
		Task savedTask;
		if (taskDTO.getMilestone_id()!= null) {
			savedTask = taskService.update(TaskMapper.toTask(taskDTO));
		}else {
			savedTask = taskService.update(TaskMapper.toTask2(taskDTO));
		}
		return new ResponseEntity<>(TaskMapper.toDTO(savedTask), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
		taskService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping(value = "/closeTask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> closeTask(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Task task = taskService.closeTask(id);
		return new ResponseEntity<>(TaskMapper.toDTO(task), HttpStatus.OK);
	}
	
	@GetMapping(value = "/allCloseTask", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getCloseTasks() {
		List<Task> tasks = taskService.findAllCloseTasks();
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for (Task task: tasks) {
			tasksDTO.add(TaskMapper.toDTO(task));
		}
		return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allOpenTask", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getOpenTasks() {
		List<Task> tasks = taskService.findAllOpenTasks();
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for (Task task: tasks) {
			tasksDTO.add(TaskMapper.toDTO(task));
		}
		return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllOpenById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getOpenTasksByProjectId(@PathVariable("id") Long id) {
		List<Task> tasks = taskService.findAllOpenTasks();
		List<Task> tasksByProjectId = taskService.findAllByProjectId(tasks, id);
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for (Task task: tasksByProjectId) {
			tasksDTO.add(TaskMapper.toDTO(task));
		}
		return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllCloseById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getCloseTasksByProjectId(@PathVariable("id") Long id) {
		List<Task> tasks = taskService.findAllCloseTasks();
		List<Task> tasksByProjectId = taskService.findAllByProjectId(tasks,id);
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for (Task task: tasksByProjectId) {
			tasksDTO.add(TaskMapper.toDTO(task));
		}
		return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
	}
	
	

	@GetMapping(value = "/getAllCloseForMilestone/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getCloseTasksForMilestone(@PathVariable("id") Long id) {
		List<Task> tasks = taskService.findAllCloseTasks();
		List<Task> tasksByProjectId = taskService.findAllForMilestone(tasks,id);
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for (Task task: tasksByProjectId) {
			tasksDTO.add(TaskMapper.toDTO(task));
		}
		return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllOpenForMilestone/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskDTO>> getOpenTasksForMilestone(@PathVariable("id") Long id) {
		List<Task> tasks = taskService.findAllOpenTasks();
		List<Task> tasksByProjectId = taskService.findAllForMilestone(tasks,id);
		List<TaskDTO> tasksDTO = new ArrayList<TaskDTO>();
		for (Task task: tasksByProjectId) {
			tasksDTO.add(TaskMapper.toDTO(task));
		}
		return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
	}



	@GetMapping(value = "/getMilestoneForTask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MilestoneDTO> getMilestoneForTask(@PathVariable("id") Long id) {
		Task task = taskService.findOne(id);
		MilestoneDTO DT0 = new MilestoneDTO();
		if (task.getMilestone()!=null) {
			return new ResponseEntity<>(MilestoneMapper.toDTO(task.getMilestone()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(DT0, HttpStatus.OK);
		}
	}


	@PostMapping(value = "/setMilestoneToTask", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MilestoneDTO> setMilestoneToTask(@RequestBody MilestoneTaskDTO dto) {
		Milestone milestone = taskService.setMilestoneToTask(dto.getTask_id(),MilestoneMapper.toMilestone(dto.getMilestone()));		
		return new ResponseEntity<>(MilestoneMapper.toDTO(milestone), HttpStatus.OK);
	}

	@GetMapping(value = "/getUsersForTask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<UserDTO>> getUsersForTask(@PathVariable("id") Long id) {
		Task task = taskService.findOne(id);		
		Set<User> users = task.getAssigned_users();
		Set<UserDTO> usersDTO = new HashSet<UserDTO>();
		for (User user: users) {
			usersDTO.add(UserMapper.toDTO(user));
		}
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/setUsersToTask", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<UserDTO>> setUsersToTask(@RequestBody UserTaskDTO dto) {
		Set<User> users = new HashSet<>();
		for (UserDTO user : dto.getUsers()) {
			users.add(UserMapper.toUser(user));
		}
		Set<User> returnUser = taskService.setUsersToTask(dto.getTask_id(),users);	
		Set<UserDTO> returnDTO = new HashSet<>();
		for (User user : returnUser) {
			returnDTO.add(UserMapper.toDTO(user));
		}

		return new ResponseEntity<>(returnDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getLabelsForTask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<LabelResource>> getLabelsForTask(@PathVariable("id") Long id) {
		Task task = taskService.findOne(id);		
		Set<LabelEntity> labels = task.getLabels();
		Set<LabelResource> labelsDTO = new HashSet<LabelResource>();
		for (LabelEntity label: labels) {
			labelsDTO.add(LabelResource.entityToResource(label));
		}
		return new ResponseEntity<>(labelsDTO, HttpStatus.OK);
	}
	
	// DODATI
	@PostMapping(value = "/setLabelsToTask", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<LabelResource>> setLabelsToTask(@RequestBody LabelTaskDTO dto) {
		/*
		Set<User> users = new HashSet<>();
		for (UserDTO user : dto.getUsers()) {
			users.add(UserMapper.toUser(user));
		}
		Set<User> returnUser = taskService.setUsersToTask(dto.getTask_id(),users);	
		Set<UserDTO> returnDTO = new HashSet<>();
		for (User user : returnUser) {
			returnDTO.add(UserMapper.toDTO(user));
		}*/

		Set<LabelResource> returnDTO = new HashSet<>();
		return new ResponseEntity<>(returnDTO, HttpStatus.OK);
	}
	

}
