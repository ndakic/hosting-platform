package uns.ac.rs.hostplatserver.controller;

import java.util.ArrayList;
import java.util.List;

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

import uns.ac.rs.hostplatserver.dto.TaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.mapper.TaskMapper;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Task task = taskService.findOne(id);
		return new ResponseEntity<>(TaskMapper.toDTO(task), HttpStatus.OK);
	}

	
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
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
		Task savedTask;
		if (taskDTO.getLabels()!= null) {
			savedTask = taskService.create(TaskMapper.toTask(taskDTO));
		}else {
			savedTask = taskService.create(TaskMapper.toTask2(taskDTO));
		}
		return new ResponseEntity<>(TaskMapper.toDTO(savedTask), HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) throws Exception {
		Task updatedTask = taskService.update(TaskMapper.toTask(taskDTO));
		return new ResponseEntity<>(TaskMapper.toDTO(updatedTask), HttpStatus.OK);
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
	

}
