package demo.security_spring.Repository;

import demo.security_spring.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositoryRole extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
}
