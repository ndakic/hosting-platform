package uns.ac.rs.hostplatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.hostplatserver.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

}
