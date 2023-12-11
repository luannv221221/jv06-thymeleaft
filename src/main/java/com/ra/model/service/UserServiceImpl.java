package com.ra.model.service;

import com.ra.model.dao.UserDAO;
import com.ra.model.dto.user.UserRegisterDTO;
import com.ra.model.dto.user.response.UserResponseDTO;
import com.ra.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Boolean register(UserRegisterDTO user) {
        User user1 = new User();
        user1.setFullName(user.getFullName());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
//        User user1 = new ModelMapper().map(user,User.class);
        // mã hóa mật khẩu
        String hasPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user1.setPassword(hasPassword);

        return userDAO.register(user1);
    }

    @Override
    public UserResponseDTO login(String email, String password) {
        User user = userDAO.findByEmail(email);

        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                UserResponseDTO userResponse = new UserResponseDTO();
                userResponse.setId(user.getId());
                userResponse.setFullName(user.getFullName());
                userResponse.setPhone(user.getPhone());
                userResponse.setEmail(user.getEmail());
                userResponse.setRole(user.getRole());
                return userResponse;
            }

        }
        return null;
    }
}
