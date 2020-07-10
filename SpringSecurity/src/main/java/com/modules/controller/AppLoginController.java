package com.modules.controller;


import com.modules.entities.User;
import com.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

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

    @PostMapping("/doLogin")
    public String  doLogin(HttpSession session, String username, String password, Model model){
        String pass = userService.selectPassword(username);
        System.out.println(pass);
        if(password.equals(pass)){
            session.setAttribute("LoginState",username);
            System.out.println(username);
            model.addAttribute("LoginState",username);
            // 重定向  : 名字 在默认视图解析器里配置
            return "redirect:/home";
        }
        model.addAttribute("loginMsg","用户名或密码错误！");
        return "main/login";
    }

//    @GetMapping(value = "/login")
//    public String login(Model model, User user, @RequestParam(value = "error",required = false) boolean error
//                       ,@RequestParam(value = "logout",required = false) boolean logout, HttpSession session){
//
//        System.out.println(user);
//
//             model.addAttribute(user);
//             //如果已经登陆跳转到个人首页
//             Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//             if(authentication!= null && !authentication.getPrincipal().equals("anonymousUser") && authentication.isAuthenticated()){
//                 System.out.println("成功");
//                 return "main/home";
//             }
//             if(error==true){
//                 model.addAttribute("error",error);
//             }
//             if(logout==true){
//                 model.addAttribute("logout",logout);
//             }
//
//         session.setAttribute("LoginState",user.getUsername());
//         return "main/login";
//    }

}
