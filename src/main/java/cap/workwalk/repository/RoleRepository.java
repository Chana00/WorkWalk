package cap.workwalk.repository;

import cap.workwalk.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRolename(String rolename);
}
