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

import uns.ac.rs.hostplatserver.dto.ProjectDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.mapper.ProjectMapper;
import uns.ac.rs.hostplatserver.model.Project;
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
}
