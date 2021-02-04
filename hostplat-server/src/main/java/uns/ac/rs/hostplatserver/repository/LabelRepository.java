package uns.ac.rs.hostplatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.ac.rs.hostplatserver.model.LabelEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<LabelEntity, Long> {

    Optional<LabelEntity> findOneByMd5h(String md5h);

    List<LabelEntity> findAllByStatus_Id(long statusId);
}
