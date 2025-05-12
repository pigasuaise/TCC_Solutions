package com.example.apptccnodata.TCC_Dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.apptccnodata.Configuration.UserData.Usuario;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/dashboard")
public class InitialDashboard {
    @GetMapping
    public String showDashboard(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/login?error=unauthorized";
        } 
        
        model.addAttribute("usuario", usuario);

        return "dashboard";
    }
    
}
