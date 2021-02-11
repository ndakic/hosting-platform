package uns.ac.rs.hostplatserver.mapper;

import java.util.stream.Collectors;

import uns.ac.rs.hostplatserver.dto.ProjectDTO;
import uns.ac.rs.hostplatserver.model.Project;

public class ProjectMapper {
		
	public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(project.getId(),
        		project.getName(),
        		project.getDescription(),
        		project.getCreate_date(),
        		project.getUsers().stream().map(UserMapper::toDTO).collect(Collectors.toSet()),
        		project.isPrivate_project()
        		
        );
	}
    
    public static Project toProject(ProjectDTO projectDTO) {
        return new Project(projectDTO.getId(),
        		projectDTO.getName(), 
        		projectDTO.getDescription(),
        		projectDTO.getUsers().stream().map(UserMapper::toUser).collect(Collectors.toSet()),
        		projectDTO.isPrivate_project()
            );    
    }

}
