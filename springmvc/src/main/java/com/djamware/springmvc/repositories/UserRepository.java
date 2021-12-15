package com.djamware.springmvc.repositories;

import com.djamware.springmvc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(final String email);

}