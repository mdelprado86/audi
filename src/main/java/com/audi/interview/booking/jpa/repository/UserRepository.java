package com.audi.interview.booking.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.audi.interview.booking.jpa.domain.User;

/**
 * For Spring Data JPA query methods see:
 * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    public List<User> findByLastNameIn(List<String> lastNames);

}