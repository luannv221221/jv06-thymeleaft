package com.ra;


import org.mindrot.jbcrypt.BCrypt;

public class Test {
    public static void main(String[] args) {
       String pass =  BCrypt.hashpw("123456",BCrypt.gensalt(12));
       System.out.println(pass);
        System.out.println(BCrypt.checkpw("1234567",pass));
    }
}
