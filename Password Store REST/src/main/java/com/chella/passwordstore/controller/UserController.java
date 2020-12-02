package com.chella.passwordstore.controller;

import com.chella.passwordstore.Constants;
import com.chella.passwordstore.model.User;
import com.chella.passwordstore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User user) {
        if (repository.login(user)) {
            return new ResponseEntity<>(Constants.LOGIN_SUCCESSFUL, HttpStatus.OK);
        }
        return new ResponseEntity<>(Constants.LOGIN_FAILURE, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody User user) {
        if (repository.register(user)) {
            return new ResponseEntity<>(Constants.REGISTRATION_SUCCESSFUL, HttpStatus.OK);
        }
        return new ResponseEntity<>(Constants.ALREADY_REGISTERED, HttpStatus.FORBIDDEN);
    }

}
