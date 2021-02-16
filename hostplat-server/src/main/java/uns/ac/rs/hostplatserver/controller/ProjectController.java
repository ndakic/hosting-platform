package uns.ac.rs.hostplatserver.controller;

import java.util.ArrayList;
import java.util.List;
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

import uns.ac.rs.hostplatserver.dto.MilestoneDTO;
import uns.ac.rs.hostplatserver.dto.ProjectDTO;
import uns.ac.rs.hostplatserver.dto.UserDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.mapper.MilestoneMapper;
import uns.ac.rs.hostplatserver.mapper.ProjectMapper;
import uns.ac.rs.hostplatserver.mapper.UserMapper;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Project;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDTO> getProject(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Project project = projectService.findOne(id);
		return new ResponseEntity<>(ProjectMapper.toDTO(project), HttpStatus.OK);
	}

	
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> getProjects() {
		List<Project> projects = projectService.findAll();
		List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>();
		for (Project project: projects) {
			projectsDTO.add(ProjectMapper.toDTO(project));
		}
		return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) throws Exception {
		System.out.println(projectDTO.getUsers().toString());
		Project savedProject = projectService.create(ProjectMapper.toProject(projectDTO));
		return new ResponseEntity<>(ProjectMapper.toDTO(savedProject), HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO) throws Exception {
		Project updatedProject = projectService.update(ProjectMapper.toProject(projectDTO));
		return new ResponseEntity<>(ProjectMapper.toDTO(updatedProject), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id) {
		projectService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/allForUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> getProjectsForUser(@PathVariable("id") Long id) {
		List<Project> projects = projectService.findAllForUser(id);
		List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>();
		for (Project project: projects) {
			projectsDTO.add(ProjectMapper.toDTO(project));
		}
		return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allPublic", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDTO>> getPublic() {
		List<Project> projects = projectService.findAllPublic();
		List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>();
		for (Project project: projects) {
			projectsDTO.add(ProjectMapper.toDTO(project));
		}
		return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allUserForProject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getUsersForProject(@PathVariable("id") Long id) {
		Set<User> users = projectService.findAllUsersForProject(id);
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (User user: users) {
			usersDTO.add(UserMapper.toDTO(user));
		}
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/allMilestoneForProject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MilestoneDTO>> getMilestoneForProjectWithTask(@PathVariable("id") Long id) {
		List<Milestone> milestones = projectService.findAllMilestonesForProjectWithTask(id);
		List<MilestoneDTO> milestonesDTO = new ArrayList<MilestoneDTO>();
		for (Milestone milestone: milestones) {
			milestonesDTO.add(MilestoneMapper.toDTO(milestone));
		}
		return new ResponseEntity<>(milestonesDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/updatePrivateProject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProjectDTO> updatePrivateProject(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Project project = projectService.findOne(id);
		if(project.isPrivate_project()) {
			project.setPrivate_project(false);
		}else {
			project.setPrivate_project(true);
		}
		return new ResponseEntity<>(ProjectMapper.toDTO(project), HttpStatus.OK);
	}
	
	@GetMapping(value = "/allMilestone/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MilestoneDTO>> getMilestoneForProject(@PathVariable("id") Long id) {
		List<Milestone> milestones = projectService.findAllMilestonesForProject(id);
		List<MilestoneDTO> milestonesDTO = new ArrayList<MilestoneDTO>();
		for (Milestone milestone: milestones) {
			milestonesDTO.add(MilestoneMapper.toDTO(milestone));
		}
		return new ResponseEntity<>(milestonesDTO, HttpStatus.OK);
	}
	
}

