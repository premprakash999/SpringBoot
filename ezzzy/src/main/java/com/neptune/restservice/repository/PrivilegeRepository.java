package com.neptune.restservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neptune.restservice.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
