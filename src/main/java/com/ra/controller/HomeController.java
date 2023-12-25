package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HttpSession session;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/")
    public String index(Model model){
        List<Category> list = categoryService.findAll();
        model.addAttribute("list",list);
        return "home";
    }

    @GetMapping("/add")
    public String addCategory(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "add";
    }
    @PostMapping("/add")
    public String createCategory(@ModelAttribute("category") Category category){
        if(categoryService.saveOrUpdate(category)) {
            return "redirect:/";
        }
        return "add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id,@ModelAttribute("category") Category category){
        if(categoryService.saveOrUpdate(category)){
            return "redirect:/";
        }
        return "redirect:/edit/"+id;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/";

    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
