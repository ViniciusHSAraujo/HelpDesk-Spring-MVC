package com.vhsadev.helpdesk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vhsadev.helpdesk.Models.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
