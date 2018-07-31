package com.audi.interview.booking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.audi.interview.booking.jpa.domain.User;
import com.audi.interview.booking.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> index() {
        log.debug("Getting all users");
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsersByLastNames(@RequestParam(required = true) List<String> lastNames) {
        return userService.findUsersByLastNames(lastNames);
    }
}
