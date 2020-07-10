package com.modules.controller;


import com.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    @GetMapping({"/","/home"})
    public String home(){
        return "main/index";
    }

    @GetMapping("/login")
    public String login(){
        return "main/login";
    }

    // 需要这个 ROLE_emp角色，才能访问
    @Secured("ROLE_manager")
    @GetMapping("/test")
    public String test(){
        return "main/test";
    }

    @GetMapping("/unverified")
    public String unverified(){
        return "main/unverified";
    }

    @GetMapping("/level1/{id}")
    public String level1(@PathVariable("id") String id){
        return "level1/index" + id;
    }

    @GetMapping("/level2/{id}")
    public String level2(@PathVariable("id") String id){
        return "level2/index" + id;
    }

    @GetMapping("/level3/{id}")
    public String level3(@PathVariable("id") String id){
        return "level3/index" + id;
    }

}
