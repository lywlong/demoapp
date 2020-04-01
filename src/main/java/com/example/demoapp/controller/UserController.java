package com.example.demoapp.controller;

import com.example.demoapp.Entity.UserEntity;
import com.example.demoapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserService, UserEntity,Long> {
}
