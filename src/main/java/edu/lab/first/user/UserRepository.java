package edu.lab.first.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query("select user from User user where user.email=?1")
    public Optional<User> findByEmail(String email);
}
