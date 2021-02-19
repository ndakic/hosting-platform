package uns.ac.rs.hostplatserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uns.ac.rs.hostplatserver.constant.LabelStatus;
import uns.ac.rs.hostplatserver.dto.MilestoneTaskDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.model.StatusEntity;
import uns.ac.rs.hostplatserver.model.Task;
import uns.ac.rs.hostplatserver.repository.MilestoneRepository;
import uns.ac.rs.hostplatserver.repository.StatusRepository;
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
	
	@Autowired
	private StatusRepository statusRepository;
	
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
        milestone.setStatus(statusRepository.getOne((long) 40));
        
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
		Milestone milestone = this.findOne(id);
        milestone.setStatus(statusRepository.getOne((long) 41));
        milestoneRepository.save(milestone);
		
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


	
}
