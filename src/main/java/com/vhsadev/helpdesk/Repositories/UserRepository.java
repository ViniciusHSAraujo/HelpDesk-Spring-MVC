package com.vhsadev.helpdesk.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vhsadev.helpdesk.Models.Role;
import com.vhsadev.helpdesk.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
