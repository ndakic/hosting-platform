package uns.ac.rs.hostplatserver.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.dto.StatisticBackDTO;
import uns.ac.rs.hostplatserver.dto.StatisticsDTO;
import uns.ac.rs.hostplatserver.dto.TaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.mapper.TaskMapper;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Project;
import uns.ac.rs.hostplatserver.model.Task;
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
		
	
		return this.projectRepository.save(projectToUpdate);
	}

	@Override
	public void delete(Long id) {
		Project project = findOne(id);
		project.setUsers(new HashSet<>());
		projectRepository.save(project);
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

	@Override
	public Set<User> findAllUsersForProject(Long id) {
		Project project = taskService.findOne(id).getProject();
		return project.getUsers();

	}

	@Override
	public List<Milestone> findAllMilestonesForProjectWithTask(Long id) {
		Project project = taskService.findOne(id).getProject();
		List<Task> allTask = taskService.findAll();
		List<Task> tasks = taskService.findAllByProjectId(allTask, project.getId());
		List<Milestone> milestones = new ArrayList<>();
		for (Task t : tasks) {
			if (!milestones.contains(t.getMilestone()) & t.getMilestone()!=null) {
				milestones.add(t.getMilestone());
			}
		}
		return milestones;
		
	}
	
	@Override
	public List<Milestone> findAllMilestonesForProject(Long id) {
		Project project = this.findOne(id);
		List<Task> allTask = taskService.findAll();
		List<Task> tasks = taskService.findAllByProjectId(allTask, project.getId());
		List<Milestone> milestones = new ArrayList<>();
		for (Task t : tasks) {
			if (!milestones.contains(t.getMilestone())) {
				milestones.add(t.getMilestone());
			}
		}
		return milestones;
		
	}

	@Override
	public Set<User> setUsersToProject(Long project_id, Set<User> usersOnProject, Set<User> usersSaFronta) {
		usersOnProject.addAll(usersSaFronta);
		Set<User> all = new HashSet<>();
		Project project = this.findOne(project_id);
		List<User> allUser = userService.getAll();
		for(User us: allUser) {
			for(User u: usersOnProject) {
				if(u.getId().equals(us.getId())) {
					all.add(us);
				}
			}
		}
		
		
		project.setUsers(all);
		projectRepository.save(project);
		return all;
	}

	@Override
	public StatisticBackDTO statistic(StatisticsDTO dto) {
		List<Task> openTasks = taskService.findAllOpenTasks();
		List<Task> openTasksByProjectId = taskService.findAllByProjectId(openTasks, dto.getProject_id());
		List<Task> closeTasks = taskService.findAllCloseTasks();
		List<Task> closeTasksByProjectId = taskService.findAllByProjectId(closeTasks, dto.getProject_id());
		Calendar cal = Calendar.getInstance();
		List<Task> closeTask = new ArrayList<>();
		List<Task> openTask = new ArrayList<>();

		if(dto.getPeriod().equals("24 h")){
			cal.add(Calendar.DATE, -1);
			Date cal2 = cal.getTime();
			for (Task t: closeTasksByProjectId) {
				if(cal2.before(t.getEnd_date())) {
					System.out.println("24 h");
					closeTask.add(t);				
				}
			}
			for(Task t:  openTasksByProjectId) {
				if(cal2.before(t.getCreate_date())) {
					System.out.println("TU");
					openTask.add(t);
				}
			}
		}else if(dto.getPeriod().equals("3 days")) {
			cal.add(Calendar.DATE, -3);
			Date cal2 = cal.getTime();
			for (Task t: closeTasksByProjectId) {
				if(t.getEnd_date()!=null &  cal2.before(t.getEnd_date()) ) {
					System.out.println("3 days");
					closeTask.add(t);				
				}
			}
			for(Task t: openTasksByProjectId) {
				if(cal2.before(t.getCreate_date())) {
					openTask.add(t);
				}
			}
		
		}else if(dto.getPeriod().equals("1 week")) {
			cal.add(Calendar.DATE, -7);
			Date cal2 = cal.getTime();
			for (Task t: closeTasksByProjectId) {
				if(t.getEnd_date()!=null &  cal2.before(t.getEnd_date()) ) {
					System.out.println("7 days");
					closeTask.add(t);				
				}
			}
			for(Task t: openTasksByProjectId) {
				if(cal2.before(t.getCreate_date())) {
					openTask.add(t);
				}
			}
		
		}else if(dto.getPeriod().equals("1 month")) {
			cal.add(Calendar.DATE, -30);
			Date cal2 = cal.getTime();
			for (Task t: closeTasksByProjectId) {
				if(t.getEnd_date()!=null &  cal2.before(t.getEnd_date()) ) {
					System.out.println("30 days");
					closeTask.add(t);				
				}
			}
			for(Task t: openTasksByProjectId) {
				if(cal2.before(t.getCreate_date())) {
					openTask.add(t);
				}
			}
		
		}
		
		List<TaskDTO> openDTO = new ArrayList<TaskDTO>();
		for (Task t: openTask) {
			openDTO.add(TaskMapper.toDTO(t));
		}
		
		List<TaskDTO> closeDTO = new ArrayList<TaskDTO>();
		for (Task t: closeTask) {
			closeDTO.add(TaskMapper.toDTO(t));
		}
		
		StatisticBackDTO statistic = new StatisticBackDTO(openDTO, closeDTO);
		
		return statistic;

	}

}
