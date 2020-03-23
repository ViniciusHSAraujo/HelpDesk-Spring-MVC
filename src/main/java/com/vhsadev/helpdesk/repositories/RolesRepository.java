package com.vhsadev.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vhsadev.helpdesk.Models.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {

}
