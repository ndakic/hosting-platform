package uns.ac.rs.hostplatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ac.rs.hostplatserver.model.StatusEntity;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
