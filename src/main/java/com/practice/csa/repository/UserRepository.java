package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.csa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
