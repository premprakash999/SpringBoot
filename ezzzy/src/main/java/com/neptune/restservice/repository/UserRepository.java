package com.neptune.restservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neptune.restservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}
