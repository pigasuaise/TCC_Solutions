package com.example.apptccnodata.Data_usuario;

import org.springframework.stereotype.Service;

import com.example.apptccnodata.Configuration.UserData.Usuario;
import com.example.apptccnodata.Configuration.UserData.UsuarioRepository;

@Service
public class userSearchService {

    private final UsuarioRepository usuarioRepository;

    // Injeção de dependência via construtor
    public userSearchService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}