package com.ra.model.service;

import com.ra.model.dto.user.UserRegisterDTO;
import com.ra.model.dto.user.response.UserResponseDTO;
import com.ra.model.entity.User;

public interface UserService {
    Boolean register(UserRegisterDTO user);
    UserResponseDTO login(String email, String password);
}
