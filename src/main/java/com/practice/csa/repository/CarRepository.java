package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.csa.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
