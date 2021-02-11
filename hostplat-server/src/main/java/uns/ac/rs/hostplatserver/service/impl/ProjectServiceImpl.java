package uns.ac.rs.hostplatserver.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Project;
import uns.ac.rs.hostplatserver.model.User;
import uns.ac.rs.hostplatserver.repository.ProjectRepository;
import uns.ac.rs.hostplatserver.service.ProjectService;
import uns.ac.rs.hostplatserver.service.TaskService;
import uns.ac.rs.hostplatserver.service.UserService;
import uns.ac.rs.hostplatserver.util.DateUtil;

@Service
public class ProjectServiceImpl implements ProjectService {
	

	@Autowired
    private ProjectRepository projectRepository;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private TaskService taskService;

	@Override
	public Project findOne(Long id) throws ResourceNotFoundException {
		
		return this.projectRepository.findById(id)
	        		.orElseThrow(
	                ()-> new ResourceNotFoundException(String.format("Project with id %s not found!", id))
	       );
	}

	@Override
	public List<Project> findAll() {
		return this.projectRepository.findAll();

	}

	@Override
	public Project create(Project project) throws Exception {
		project.setCreate_date(DateUtil.nowSystemTime());
		Set<User> users = new HashSet<>();

		for (User user : project.getUsers()) {
			User u = userService.findOne(user.getId());
			users.add(u);
		}
		
		project.setUsers(users);
        return this.projectRepository.save(project);
       

	}

	@Override
	public Project update(Project project) throws Exception {
		Project projectToUpdate = this.findOne(project.getId());
		if(project.getName()!=null) {
			projectToUpdate.setName(project.getName());
		}
		if(project.getDescription()!= null) {
			projectToUpdate.setDescription(project.getDescription());
		}
		Set<User> users = new HashSet<>();

		for (User user : project.getUsers()) {
			User u = userService.findOne(user.getId());
			users.add(u);
		}
				
		projectToUpdate.setUsers(users);
	
		return this.projectRepository.save(projectToUpdate);
	}

	@Override
	public void delete(Long id) {
		Project project = findOne(id);
		project.setUsers(new HashSet<>());
		projectRepository.save(project);
		//taskService.deleteByProjectId(id);
		this.projectRepository.deleteById(id);
		
	}

	@Override
	public List<Project> findAllForUser(Long id) {
		return this.projectRepository.findAllByUsersId(id);

	}

	@Override
	public List<Project> findAllPublic() {
		List<Project> allPublic = new ArrayList<>();
		
		for (Project project : projectRepository.findAll()) {
			if(!project.isPrivate_project()) {
				allPublic.add(project);
			}
		}
		
		
		return allPublic;

	}

}
