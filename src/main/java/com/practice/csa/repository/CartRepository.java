package com.practice.csa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.csa.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
