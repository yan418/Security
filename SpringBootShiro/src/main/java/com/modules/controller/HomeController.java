package com.modules.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
