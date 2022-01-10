package edu.lab.first.role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(path = "api/v1/role")
public class RoleController {

    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> getRoles() {
        return service.getRoles();
    }

    @GetMapping(path = "{roleId}")
    public Role getRole(
            @PathVariable("roleId") Long roleId
    ) {
        return service.getRole(roleId);
    }

    @PostMapping
    public Role createRole(@RequestBody RoleDto roleDto) {
        return service.createRole(roleDto);
    }

    @PutMapping(path = "{roleId}")
    public Role update(
            @PathVariable("roleId") Long userId,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Set<Long> users
    ) {
        return service.update(userId, role, description, users);
    }

    @DeleteMapping(path = "{roleId}")
    public void delete(@PathVariable("roleId") Long roleId){
        service.delete(roleId);
    }

}
