package com.ra.model.dao;

import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Boolean saveOrUpdate(Category category);
    void delete(Integer id);
    Category findById(Integer id);

}
