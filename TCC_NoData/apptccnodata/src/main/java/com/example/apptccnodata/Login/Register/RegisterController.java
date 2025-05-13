package com.example.apptccnodata.Login.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.apptccnodata.Configuration.UserData.Usuario;
import com.example.apptccnodata.Configuration.UserData.UsuarioRepository;
import com.example.apptccnodata.Login.PasswordEncrypter;

import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class RegisterController {
    PasswordEncrypter pe = new PasswordEncrypter();

    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/registro")
    public String showRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/registro";
    }
    
    @PostMapping("/registrar")
    public String registrarUsuario(Usuario usuario, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("Passou no /registrar");

        String password = usuario.getSenha();
        password = pe.hash(password);
        usuario.setSenha(password);

        System.out.println("Senha hashada!");


        if(result.hasErrors()) {
            System.err.println("Erro encontrado!");
            result.getAllErrors().forEach(error -> System.err.println(error.toString()));
            return "registro";
        }
        
        if(ur.findByEmail(usuario.getEmail()) != null) {
            model.addAttribute("error", "Email já registrado!");
            return "registro";
        }

        ur.save(usuario);
        redirectAttributes.addFlashAttribute("sucess", "Registro realizado com sucesso!");
        System.out.println("Usuario registrado!");
        return "redirect:/index";
    }
    
    
}