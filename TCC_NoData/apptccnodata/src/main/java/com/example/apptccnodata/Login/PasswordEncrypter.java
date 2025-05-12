package com.example.apptccnodata.Login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncrypter {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String hash(String senha) {
        return passwordEncoder.encode(senha);
    }

}

