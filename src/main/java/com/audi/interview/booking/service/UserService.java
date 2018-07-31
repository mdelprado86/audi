package com.audi.interview.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.audi.interview.booking.exceptions.NotFoundException;
import com.audi.interview.booking.jpa.domain.User;
import com.audi.interview.booking.jpa.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        Assert.notNull(user, "User must not be null");
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        Assert.notNull(id, "Id must not be null");

        User user = userRepository.findOne(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    public User findByEmail(String email) {
        Assert.hasLength(email, "Email must not be empty");
        return userRepository.findByEmail(email);
    }

    public List<User> findUsersByLastNames(List<String> lastNames) {
        return userRepository.findByLastNameIn(lastNames);
    }
}