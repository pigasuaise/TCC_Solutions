package com.example.apptccnodata.SessionController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apptccnodata.Configuration.UserData.Usuario;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionController {
    
    @Autowired
    private HttpSession session;

    public Usuario getLoggedUser() {
        return (Usuario) session.getAttribute("usuarioLogado");
    }

    public String getLoggedUserName() {
        Usuario usuario = getLoggedUser();
        return usuario != null ? usuario.getNome() : "Nulo";
    }
}
