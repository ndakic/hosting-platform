package uns.ac.rs.hostplatserver.repository;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uns.ac.rs.hostplatserver.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	@Query("SELECT task from Task task WHERE task.project.id = ?1")
	List<Task> findAllByProjectId(Long id);

}
