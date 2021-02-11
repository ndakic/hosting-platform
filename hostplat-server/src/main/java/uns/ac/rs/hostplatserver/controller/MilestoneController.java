package uns.ac.rs.hostplatserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import uns.ac.rs.hostplatserver.dto.MilestoneDTO;
import uns.ac.rs.hostplatserver.exception.ResourceNotFoundException;
import uns.ac.rs.hostplatserver.mapper.MilestoneMapper;
import uns.ac.rs.hostplatserver.model.Milestone;
import uns.ac.rs.hostplatserver.service.MilestoneService;

@RestController
@RequestMapping("/api/milestone")
public class MilestoneController {

	@Autowired
	private MilestoneService milestoneService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MilestoneDTO> getMilestone(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Milestone milestone = milestoneService.findOne(id);
		return new ResponseEntity<>(MilestoneMapper.toDTO(milestone), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MilestoneDTO>> getMilestones() {
		List<Milestone> milestones = milestoneService.findAll();
		List<MilestoneDTO> milestonesDTO = new ArrayList<MilestoneDTO>();
		for (Milestone milestone: milestones) {
			milestonesDTO.add(MilestoneMapper.toDTO(milestone));
		}
		return new ResponseEntity<>(milestonesDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MilestoneDTO> createMilestone(@RequestBody MilestoneDTO milestoneDTO) throws Exception {
		Milestone savedMilestone = milestoneService.create(MilestoneMapper.toMilestone(milestoneDTO));
		return new ResponseEntity<>(MilestoneMapper.toDTO(savedMilestone), HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MilestoneDTO> updateMilestone(@RequestBody MilestoneDTO milestoneDTO) throws Exception {
		Milestone updatedMilestone = milestoneService.update(MilestoneMapper.toMilestone(milestoneDTO));
		return new ResponseEntity<>(MilestoneMapper.toDTO(updatedMilestone), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Milestone> deleteMilestone(@PathVariable("id") Long id) {
		milestoneService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
