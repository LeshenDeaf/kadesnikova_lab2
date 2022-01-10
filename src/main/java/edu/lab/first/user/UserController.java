package edu.lab.first.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUser(
        @PathVariable("userId") Long userId
    ) {
        return service.getUser(userId);
    }

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        return service.createUser(userDto);
    }

    @PutMapping(path = "{userId}")
    public User update(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Set<Long> roles
    ) {
        return service.update(userId, email, firstName, secondName, lastName, roles);
    }

    @DeleteMapping(path = "{userId}")
    public void delete(@PathVariable("userId") Long userId){
        service.delete(userId);
    }

}
