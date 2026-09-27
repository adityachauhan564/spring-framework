package com.jbdl63.digitallibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jbdl63.digitallibrary.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
