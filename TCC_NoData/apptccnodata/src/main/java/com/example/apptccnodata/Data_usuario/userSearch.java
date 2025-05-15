package com.example.apptccnodata.Data_usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.apptccnodata.Configuration.UserData.Usuario;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class userSearch {

    private final userSearchService userSearchService;

    public userSearch(userSearchService userSearchService) {
        this.userSearchService = userSearchService;
    }

    @GetMapping("/usuarioData")
    public String showPage() {
        return "usuarioData";
    }

    @PostMapping("/procurarEmail")
    @ResponseBody  // Para retornar o objeto diretamente como JSON
    public Usuario mostrarUsuariosPorEmail(@RequestParam String email) {
        Usuario usuario = userSearchService.buscarPorEmail(email);

        if (usuario != null) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Nome: " + usuario.getNome());
            return usuario;
        } else {
            System.out.println("Nenhum usuário encontrado!");
            return null;
        }
    }

    @PostMapping("/procurarMensagem")
    public String mostrarMensagensPorId(@RequestParam String id) {
        System.out.println(id);
        return "/index";
    }
    
}