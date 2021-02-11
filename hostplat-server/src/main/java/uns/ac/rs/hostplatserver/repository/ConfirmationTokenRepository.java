package uns.ac.rs.hostplatserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.hostplatserver.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    ConfirmationToken findByToken(String token);
}
