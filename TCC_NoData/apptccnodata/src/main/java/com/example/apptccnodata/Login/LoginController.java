package com.example.apptccnodata.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.apptccnodata.Configuration.UserData.Usuario;
import com.example.apptccnodata.Login.Services.LoginService;

import jakarta.servlet.http.HttpSession;




@Controller
public class LoginController {
    
    private final LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/index")
    public String showLogin() {
        return "index";
    }
    
    @PostMapping("/logar")
    // RequestParam vai pegar os dados do form e trazer para o backend
    public String processarLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        Usuario usuario = loginService.authenticate(email, senha);

        if(usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            System.out.println("Usuario " + usuario.getNome() + " logou");
            return "redirect:/dashboard";
        } 

        redirectAttributes.addFlashAttribute("error", "Credenciais inválidas");
        return "redirect:/index";
    }
    
}
