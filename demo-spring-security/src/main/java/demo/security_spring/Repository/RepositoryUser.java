package demo.security_spring.Repository;

import demo.security_spring.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositoryUser extends JpaRepository<UserEntity,Long> {
    //@Query("SELECT u FROM UserEntity u WHERE u.username=?1")
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
