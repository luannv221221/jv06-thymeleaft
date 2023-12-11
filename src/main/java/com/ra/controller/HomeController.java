package com.ra.controller;

import com.ra.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private HttpSession session;
    @RequestMapping("/")
    public String index(){

        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
