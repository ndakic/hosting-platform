package uns.ac.rs.hostplatserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.hostplatserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
