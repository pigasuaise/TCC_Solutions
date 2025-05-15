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
    @Autowired
    private final LoginService loginService;
    /*
     * Aqui acontece a mesma coisa do serviço do login, por mais que inútil, na visão do JAVA, o @Autowired ainda é uma 
     * anotação necessária dentro deste código.
     */
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/index") // Esta linha busca a requisição HTTP chamada get, que basicamente acontece quando você adiciona "/n" à um link, por exemplo
    public String showLogin() {
        return "index";
    }
    
    @PostMapping("/logar")
    public String processarLogin(@RequestParam String email, @RequestParam String senha, HttpSession session, RedirectAttributes redirectAttributes, Model model) {     // RequestParam vai pegar os dados do form e trazer para o backend
        /*
         * Como utilizamos diretamente a classe de loginService (cuja qual está diretamente ligada com o repositório), usar de um método
         * para a validação sempre vai ser a opção mais segura.
         * 
         * Então aqui nós acessaremos a classe e buscaremos o método .authenticate para utilizar dos parametros do formulario HTML
         * para efetuar o login da forma correta, evitando linhas exageradas.
         */
        Usuario usuario = loginService.authenticate(email, senha);

        if(usuario != null) { // Validamos se o retorno de usuário não é nulo. Se deve basicamente pelo reinicio da página ou debug de código, isso apaga a sessão!
            session.setAttribute("usuarioLogado", usuario);
            System.out.println("Usuario " + usuario.getNome() + " logou");
            return "redirect:/dashboard";
        } 

        // Caso o usuário seja nulo, poderiamos, de fato, utilizar um else, mas optamos pela unica ultima opção lógica.
        redirectAttributes.addFlashAttribute("error", "Credenciais inválidas");
        return "redirect:/index";
    }
    
}
