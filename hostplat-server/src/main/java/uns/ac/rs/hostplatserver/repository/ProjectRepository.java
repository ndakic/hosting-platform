package uns.ac.rs.hostplatserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uns.ac.rs.hostplatserver.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

	List<Project> findAllByUsersId(Long id);

}
