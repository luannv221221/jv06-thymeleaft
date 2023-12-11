package com.ra.model.dao;

import com.ra.model.entity.User;

public interface UserDAO {
    Boolean register(User user);
    User findByEmail(String email);

}
