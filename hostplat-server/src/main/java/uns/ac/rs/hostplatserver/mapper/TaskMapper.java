package uns.ac.rs.hostplatserver.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import uns.ac.rs.hostplatserver.dto.TaskDTO;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Project;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.model.User;

public class TaskMapper {
	
	
    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(),
        		task.getTitle(),
        		task.getDescription(),
        		task.getCreate_date(),
        		task.getEnd_date(),
        		task.getAuthor() == null ? null: task.getAuthor().getId(),
        		task.getAssigned_users().stream().map(UserMapper::toDTO).collect(Collectors.toSet()),
        		task.getProject() == null ? null: task.getProject().getId(),
                task.getMilestone() == null ? null: task.getMilestone().getId(),
                task.getLabels().stream().map(LabelMapper::toDTO).collect(Collectors.toSet())		
        );
	}
    public static Task toTask(TaskDTO taskDTO) {
        return new Task(taskDTO.getId(),
        		taskDTO.getTitle(),
        		taskDTO.getDescription(),
        		taskDTO.getCreate_date(),
        		taskDTO.getEnd_date(),
        		new User(taskDTO.getAuthor_id()),
        		taskDTO.getAssigned_users().stream().map(UserMapper::toUser).collect(Collectors.toSet()),
        		new Project(taskDTO.getProject_id()),
        		new Milestone(taskDTO.getMilestone_id()),
        		taskDTO.getLabels().stream().map(LabelMapper::toLabel).collect(Collectors.toSet())
            );    
    }
    
    public static Task toTask2(TaskDTO taskDTO) {
        return new Task(taskDTO.getId(),
        		taskDTO.getTitle(),
        		taskDTO.getDescription(),
        		taskDTO.getCreate_date(),
        		taskDTO.getEnd_date(),
        		new User(taskDTO.getAuthor_id()),
        		taskDTO.getAssigned_users().stream().map(UserMapper::toUser).collect(Collectors.toSet()),
        		new Project(taskDTO.getProject_id()),
        		new Milestone(taskDTO.getMilestone_id()),
        		new HashSet<>()
            );    
    }

}
