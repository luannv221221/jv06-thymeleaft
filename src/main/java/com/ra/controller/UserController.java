package com.ra.controller;

import com.ra.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    HttpSession session;
    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes){

        if(user.getEmail().equals("bang@dz.com") && user.getPassword().equals("123456")){
            // login
            // lưu vào session
            session.setAttribute("user",user);
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("err","Sai CMNR");
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("user");
        return "redirect:/";
    }
}
