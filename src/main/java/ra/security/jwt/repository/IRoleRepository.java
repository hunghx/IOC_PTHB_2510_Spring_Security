package ra.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.security.jwt.entity.Role;
import ra.security.jwt.entity.RoleName;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
