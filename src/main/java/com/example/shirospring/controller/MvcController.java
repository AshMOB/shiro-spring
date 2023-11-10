package com.example.shirospring.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class MvcController {

    @GetMapping("/admin/{*path}")
    @ResponseBody
    public String adminBypass(@PathVariable String path) {
        System.out.println(path);
        return "admin Bypass page";
    }
}