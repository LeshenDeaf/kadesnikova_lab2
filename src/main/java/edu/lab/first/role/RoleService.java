package edu.lab.first.role;

import edu.lab.first.user.User;
import edu.lab.first.user.UserDto;
import edu.lab.first.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository repository;
    private final UserRepository userRepo;

    @Autowired
    public RoleService(RoleRepository repository,
                       UserRepository userRepo
    ) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    public List<Role> getRoles() {
        return repository.findAll();
    }

    public Role getRole(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new IllegalStateException("Role " + id + " not found")
        );
    }

    public Role createRole(RoleDto roleDto) {
        checkRoleName(roleDto.getRole());

        Role role = new Role(
                roleDto.getRole(),
                roleDto.getDescription()
        );
        role.setUsers(new HashSet<User>());

        if (roleDto.getUsers() != null) {
            roleDto.getUsers().forEach(userId -> role.addUser(findUserById(userId)));
        }

        repository.save(role);

        return role;
    }

    @Transactional
    public Role update(Long roleId,
                       String roleName,
                       String description,
                       Set<Long> users
    ) {
        Role role = repository.findById(roleId).orElseThrow(
                () -> new IllegalStateException("Role " + roleId + " not found")
        );

        if (!isEmpty(roleName) && checkRoleName(roleName)) {
            role.setRole(roleName);
        }

        if (!isEmpty(description)) {
            role.setDescription(description);
        }

        if (!isEmpty(users)) {
            users.forEach(userId -> role.addUser(findUserById(userId)));
        }

        return role;
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    private boolean isEmpty(Set str) {
        return str == null || str.size() <= 0;
    }

    public void delete(Long id) {
        Role role = repository.findById(id).orElseThrow(
                () -> new IllegalStateException("Role " + id + " not found")
        );

        role.getUsers().forEach((User user) -> user.removeRole(role));

        repository.delete(role);
    }

    private boolean checkRoleName(String name) {
        if (repository.findByName(name).isPresent()) {
            throw new IllegalStateException(
                    "Role already exists"
            );
        }

        return true;
    }

    private User findUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(
                () -> new IllegalStateException("User " + userId + " not found")
        );
    }
}
