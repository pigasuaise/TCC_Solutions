package com.example.apptccnodata.Login.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apptccnodata.Configuration.UserData.Usuario;
import com.example.apptccnodata.Configuration.UserData.UsuarioRepository;

@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario authenticate(String email, String rawSenha) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if(usuario != null && passwordEncoder.matches(rawSenha, usuario.getSenha())) {
            return usuario;
        }
        return null;
    }
}
