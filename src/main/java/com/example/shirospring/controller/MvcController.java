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

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            Object principal = subject.getPrincipal();
            System.out.println(principal.getClass().getName());
//            Account account = (Account) subject.getPrincipal();

            subject.getSession().setAttribute("account","zhangsan");
            boolean s = subject.isPermitted("manage");
            System.out.println(s);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String unauth(){
        return "未授权没有访问权限";
    }
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
//    @RequiresPermissions("/zz*")
    @RequestMapping("/zza")
    @ResponseBody
    public String zza(){
        return "RequiresPermissions页面";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String admin(@PathVariable String name){
        return "hello"+name+"!";
    }
    @GetMapping("/bar")
    @ResponseBody
    public String bar(){
        return "this is bar";
    }
//    @RequestMapping("/admin/**")
//    @ResponseBody
//    public String admin(){
//        return "this is admin";
//    }
//    @GetMapping("/aaa/")
//    @ResponseBody
//    public String aaa(){
//        return "this is aaa";
//    }
//@GetMapping("/admin/pass/page")
//@ResponseBody
//public String admin() {
//    return "admin page";
//}

    @GetMapping("/admin/{*path}")
    @ResponseBody
    public String adminBypass(@PathVariable String path) {
        System.out.println(path);
        return "admin Bypass page";
    }
}