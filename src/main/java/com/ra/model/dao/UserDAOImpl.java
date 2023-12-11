package com.ra.model.dao;

import com.ra.model.entity.User;
import com.ra.util.ConnectionDatabase;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements UserDAO{
    @Override
    public Boolean register(User user) {
        Connection connection= ConnectionDatabase.openConnection();
        try {
            CallableStatement callableStatement=connection.prepareCall("CALL PROC_CREATE_USER(?,?,?,?)");
            callableStatement.setString(1,user.getFullName());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setString(4,user.getPhone());
            int check=callableStatement.executeUpdate();
            if (check>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDatabase.closeConnection(connection);
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        Connection connection= ConnectionDatabase.openConnection();
        User user = null;
        try {
            CallableStatement callableStatement=connection.prepareCall("CALL PROC_FIND_EMAIL(?)");
            callableStatement.setString(1,email);
            ResultSet resultSet=callableStatement.executeQuery();

            while (resultSet.next()){
                user= new User();
                user.setId(resultSet.getInt("id"));
                user.setPhone(resultSet.getString("phone"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("role"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDatabase.closeConnection(connection);
        }
        return user;
    }
}
