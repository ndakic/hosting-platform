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
	public Milestone create(Milestone milestone, Long task_id) throws Exception {
		Task task = taskService.findOne(task_id);
		Milestone milestoneSaved = this.milestoneRepository.save(milestone);
		task.setMilestone(milestoneSaved);
		taskRepository.save(task);
		return milestoneSaved;
	}

	
	@Override
	public Milestone update(Milestone milestone) throws Exception {
		 Milestone milestoneToUpdate = this.findOne(milestone.getId());
		 if(milestone.getTitle()!=null) {
			 milestoneToUpdate.setTitle(milestone.getTitle());
		 }else if(milestone.getStart_date()!= null) {
			 milestoneToUpdate.setStart_date(milestone.getStart_date());
		 }else if(milestone.getEnd_date()!= null) {
			 milestoneToUpdate.setEnd_date(milestone.getEnd_date());
		 }
		 return this.milestoneRepository.save(milestoneToUpdate);
	}

	@Override
	public void delete(Long id) {
        this.milestoneRepository.deleteById(id);
		
	}
<<<<<<< Updated upstream
=======

	@Override
	public List<Milestone> findAllClose(List<Milestone> milestones) {
		List<Milestone> m = new ArrayList<>();
		for (Milestone mi : milestones) {
			if(mi!=null) {
				if(mi.isClose()) {
					m.add(mi);
				}
			}
		}
		return m;
	}
	
	@Override
	public List<Milestone> findAllOpen(List<Milestone> milestones) {
		List<Milestone> m = new ArrayList<>();
		for (Milestone mi : milestones) {
			if(mi!=null) {
				System.out.println(mi.getTitle());
				if(!mi.isClose()) {
					m.add(mi);
				}
			}
		}
		return m;
	}

	@Override
	public Milestone closeMilestone(Long id) {
		Milestone milestone = this.findOne(id);
		milestone.setClose(true);
		return milestoneRepository.save(milestone);
	}

	
>>>>>>> Stashed changes
	
}
