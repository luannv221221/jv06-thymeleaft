package com.ra.model.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    @NotEmpty(message = "Đừng để rỗng trường này bạn nhé")
    private String fullName;
    @NotEmpty(message = "Koong rong")
    @Email(message = "Phải có a còng bạn nhé")
    private String email;
    @Size(min = 3,max = 100,message = "Cái này từ điền")
    private String password;
    @Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b",message = "Sai cmnr")
    private String phone;
    public UserRegisterDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
