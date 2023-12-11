package com.ra.controller;

import com.ra.model.dto.user.UserRegisterDTO;
import com.ra.model.dto.user.response.UserResponseDTO;
import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    HttpSession session;
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes){
        UserResponseDTO userResponseDTO = userService.login(user.getEmail(),user.getPassword());
        if(userResponseDTO != null){
            // login
            // lưu vào session
            if(userResponseDTO.getRole() == 0){
                session.setAttribute("user",userResponseDTO);
                return "redirect:/";
            }
        }

        redirectAttributes.addFlashAttribute("err","Sai CMNR");
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserRegisterDTO user = new UserRegisterDTO();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("user") UserRegisterDTO user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        } else{
            if(userService.register(user)){
                return "redirect:/login";
            }
        }

        return "redirect:/register";
    }

    @GetMapping("/logon")
    public String logon(){
        return "admin/login";
    }
    @PostMapping("/handleLogon")
    public String handleLogon(@RequestParam("email") String email,@RequestParam("password") String password){
        UserResponseDTO userResponseDTO = userService.login(email,password);
        if(userResponseDTO != null){
           if(userResponseDTO.getRole() == 1){
               session.setAttribute("admin",userResponseDTO);
               return "redirect:/admin/";
           }
        }
        return "redirect:/logon";
    }
}
