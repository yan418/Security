package com.modules.controller;


import com.modules.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AppLoginController {

    @Autowired
    UserService userService;


    /**
     * 用户注册，生成加密密码
     * @param pass
     * @return
     */
    @GetMapping("/add/{pass}")
    @ResponseBody
    public String appUser(@PathVariable("pass") String pass){
        userService.addPass(pass);
        return pass;
    }

    // 认证失败
    @GetMapping("/logine")
    @ResponseBody
    public String logine(){
        String set = "用户名密码错误";
        return set.toString();
    }

    // 认证成功
    @RequestMapping("/success")
    public String successForward(){
        return "redirect:/home";
    }


    /**
     * 登录
     * @param username
     * @param password
     * @param model
     * @return
     */
    @PostMapping("/doLogin")
    public String  doLogin(String username, String password, Model model){
        //获取当前输入的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //登录，没有异常就说明登录成功
        try {
            subject.login(token);
//            Session session = subject.getSession();
//            model.addAttribute("LoginUser",session);
            return "redirect:/home";
        }catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
        }catch (IllegalArgumentException e){
            model.addAttribute("msg","密码加密错误");
        }catch (AuthenticationException e){
            model.addAttribute("msg","密码解密失败");
        }catch (Exception e){
            model.addAttribute("msg","用户名密码失败");
        }
        return "main/login";
    }



}
