package com.dinesh.app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinesh.app.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
