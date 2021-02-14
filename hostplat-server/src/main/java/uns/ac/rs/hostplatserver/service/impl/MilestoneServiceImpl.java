package uns.ac.rs.hostplatserver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.dto.MilestoneTaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.repository.MilestoneRepository;
import uns.ac.rs.hostplatserver.repository.TaskRepository;
import uns.ac.rs.hostplatserver.service.MilestoneService;
import uns.ac.rs.hostplatserver.service.TaskService;

@Service
public class MilestoneServiceImpl implements MilestoneService {
	
	@Autowired
    private MilestoneRepository milestoneRepository;

	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepository;
	
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

	@Override
	public Milestone addMilestoneToTask(MilestoneTaskDTO mtDTO) {
		Task task = taskService.findOne(mtDTO.getTask_id());
		Milestone milestone = this.findOne(mtDTO.getMilestone_id());
		System.out.println(task.getTitle());
		System.out.println(milestone.getTitle());

		task.setMilestone(milestone);
		taskRepository.save(task);
		return milestone;
	}
	
	
}
