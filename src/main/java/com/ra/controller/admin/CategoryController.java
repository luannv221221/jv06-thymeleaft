package com.ra.controller.admin;

import com.ra.model.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @GetMapping("/category")
    public String index(Model model){
        List<Category> list = new ArrayList<>();
        list.add(new Category(1,"Áo",true));
        list.add(new Category(2,"Quần",false));
        model.addAttribute("list",list);
        return "admin/category/index";
    }
    @GetMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "admin/category/add";
    }
    @PostMapping("/add-category")
    public String create(@ModelAttribute("category") Category category)
    {
        System.out.println(category.getCategoryName());
        System.out.println(category.isCategoryStatus());
        System.out.println("hihi Đồ Ngốc");
        return "admin/category/add";
    }
}
