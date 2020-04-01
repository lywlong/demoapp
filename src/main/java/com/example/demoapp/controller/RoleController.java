package com.example.demoapp.controller;

import com.example.demoapp.Entity.RoleEntity;
import com.example.demoapp.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController<RoleService, RoleEntity,Long>{


}
