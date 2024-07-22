package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.csa.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

}
