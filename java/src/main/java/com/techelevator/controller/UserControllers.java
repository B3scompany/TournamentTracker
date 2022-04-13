package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin

public class UserControllers {

    private final UserDao userDao;


    public UserControllers(UserDao userDao) {
        this.userDao = userDao;
    }




}
