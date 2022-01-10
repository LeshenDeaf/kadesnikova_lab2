package edu.lab.first.role;

import edu.lab.first.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository
        extends JpaRepository<Role, Long>{
    @Query("select role from Role role where role.role=?1")
    public Optional<Role> findByName(String name);

}
