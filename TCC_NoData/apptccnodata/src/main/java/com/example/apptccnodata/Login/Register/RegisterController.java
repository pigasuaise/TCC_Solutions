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
    PasswordEncrypter pe = new PasswordEncrypter(); // Chamamos a classe de encriptação de senha.

    @Autowired
    private UsuarioRepository ur; // Chamamos o repositorio, apenas para salvar os dados dentro do método de registrar.

    @GetMapping("/registro")
    public String showRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/registro";
    }
    
    @PostMapping("/registrar")
    public String registrarUsuario(Usuario usuario, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("Passou no /registrar");
        
        /*
         * Primeiramente, buscamos a senha do formulário. Pegamos a mesma e criptografamos.
         */
        String password = usuario.getSenha();
        password = pe.hash(password);
        usuario.setSenha(password);

        System.out.println("Senha hashada!");

        /*
         * Validaremos se o result tem erros. O result é apenas uma extensão de um BindingError.
         */
        if(result.hasErrors()) {
            System.err.println("Erro encontrado!");
            result.getAllErrors().forEach(error -> System.err.println(error.toString())); // Se houver erros, ele retornará para registro e irá imprimir qual foi o erro.
            return "registro";
        }
        
        if(ur.findByEmail(usuario.getEmail()) != null) { // se o email, dentro do banco de dados, for diferente de null, ou seja: Já existe este email. Irá adicionar um erro.
            model.addAttribute("error", "Email já registrado!");
            return "registro";
        }

        // Caso tudo esteja dentro dos conformes, seguirá o código normalmente
        ur.save(usuario); // .save é uma extensão do JPA/CrudRepository, serve apenas para salvar, obviamente.
        redirectAttributes.addFlashAttribute("sucess", "Registro realizado com sucesso!");
        System.out.println("Usuario registrado!");
        return "redirect:/index";
    }
    
    
}