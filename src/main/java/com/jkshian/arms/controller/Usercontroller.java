package com.jkshian.arms.controller;

import com.jkshian.arms.entity.User;
import com.jkshian.arms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Usercontroller {
    @Autowired
    private UserService service;

    @GetMapping("registration")
    public String registration(){
        return "User/registration";
    }

     @GetMapping("/UserDetails")
    public ModelAndView getAllUsers(){
         List<User> list=service.getAllUsers();
         return new ModelAndView("UserDetails","users",list);

     }
     @PostMapping("/save")
     public String addUser(@ModelAttribute User u)
     {
         service.save(u);
         return  "redirect:/UserDetails";
     }
}
