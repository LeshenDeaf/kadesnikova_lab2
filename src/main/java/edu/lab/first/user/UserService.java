package edu.lab.first.user;

import edu.lab.first.role.Role;
import edu.lab.first.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository repository;
    private final RoleRepository roleRepo;

    @Autowired
    public UserService(UserRepository repository,
                       RoleRepository roleRepo
    ) {
        this.repository = repository;
        this.roleRepo = roleRepo;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUser(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new IllegalStateException("User " + id + " not found")
        );
    }

    public User createUser(UserDto userDto) {
        checkEmail(userDto.getEmail());

        User user = new User(
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getLastName()
        );
        user.setRoles(new HashSet<Role>());

        if (userDto.getRoles() != null) {
            userDto.getRoles().forEach(roleId -> user.addRole(findRoleById(roleId)));
        }

        repository.save(user);

        return user;
    }

    @Transactional
    public User update(Long userId,
                       String email,
                       String firstName,
                       String secondName,
                       String lastName,
                       Set<Long> roles
    ) {
        User user = repository.findById(userId).orElseThrow(
                () -> new IllegalStateException("User " + userId + " not found")
        );

        if (!isEmpty(email) && checkEmail(email)) {
            user.setEmail(email);
        }

        if (!isEmpty(firstName)) {
            user.setFirstName(firstName);
        }

        if (!isEmpty(secondName)) {
            user.setSecondName(secondName);
        }

        if (!isEmpty(lastName)) {
            user.setLastName(lastName);
        }

        if (!isEmpty(roles)) {
            roles.forEach(roleId -> user.addRole(findRoleById(roleId)));
        }

        return user;
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id).orElseThrow(
                () -> new IllegalStateException("User " + id + " not found")
        ));
    }

    private boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    private boolean isEmpty(Set str) {
        return str == null || str.size() <= 0;
    }

    private boolean checkEmail(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new IllegalStateException(
                    "User with email \""
                            + email
                            + "\" already exists"
            );
        }

        return true;
    }

    private Role findRoleById(Long roleId) {
        return roleRepo.findById(roleId).orElseThrow(
                () -> new IllegalStateException("Role " + roleId + " not found")
        );
    }
}
