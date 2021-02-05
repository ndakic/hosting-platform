package uns.ac.rs.hostplatserver.mapper;

import uns.ac.rs.hostplatserver.dto.MilestoneDTO;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.User;

public class MilestoneMapper {
	
	public static MilestoneDTO toDTO(Milestone milestone) {
        return new MilestoneDTO(milestone.getId(),
        		milestone.getTitle(),
        		milestone.getStart_date(),
        		milestone.getEnd_date(),
                milestone.getUser() == null ? null: milestone.getUser().getId());
	}
    
    public static Milestone toMilestone(MilestoneDTO milestoneDTO) {
        return new Milestone(milestoneDTO.getId(),
                milestoneDTO.getTitle(), 
                milestoneDTO.getStart_date(),
                milestoneDTO.getEnd_date(),
                new User(milestoneDTO.getUser_id())
            );    
    }

}
