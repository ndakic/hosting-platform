package uns.ac.rs.hostplatserver.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.repository.MilestoneRepository;
import uns.ac.rs.hostplatserver.service.MilestoneService;

@Service
public class MilestoneServiceImpl implements MilestoneService {
	
	@Autowired
    private MilestoneRepository milestoneRepository;

	@Override
	public Milestone findOne(Long id) throws ResourceNotFoundException {
		 return this.milestoneRepository.findById(id)
	        		.orElseThrow(
	                ()-> new ResourceNotFoundException(String.format("Milestone with id %s not found!", id))
	       );
	}

	@Override
	public List<Milestone> findAll() {
		return this.milestoneRepository.findAll();
	}

	
	@Override
	public Milestone create(Milestone milestone) throws Exception {
        return this.milestoneRepository.save(milestone);

	}

	
	@Override
	public Milestone update(Milestone milestone) throws Exception {
		 Milestone milestoneToUpdate = this.findOne(milestone.getId());
		 if(milestone.getTitle()!=null) {
			 milestoneToUpdate.setTitle(milestone.getTitle());
		 }
		 if(milestone.getStart_date()!= null) {
			 milestoneToUpdate.setStart_date(milestone.getStart_date());
		 }
		 if(milestone.getEnd_date()!= null) {
			 milestoneToUpdate.setEnd_date(milestone.getEnd_date());
		 }
		 return this.milestoneRepository.save(milestoneToUpdate);
	}

	@Override
	public void delete(Long id) {
        this.milestoneRepository.deleteById(id);
		
	}
	
}
