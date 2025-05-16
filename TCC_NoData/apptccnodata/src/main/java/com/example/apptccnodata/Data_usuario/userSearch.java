package com.example.apptccnodata.Data_usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.apptccnodata.Configuration.UserData.Usuario;


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

    /*
     * Nesta classe, como é definida como um tipo de dado é necessário um retorno
     * 
     * O único problema disso é que não posso utiliza-la aqui, então em teoria seria mais fácil criar
     * uma classe, controlada pelo Spring, para retornar usuario como um tipo de dado.
     */
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
    /*
     * Aqui é o método principal dessa classe, basicamente se iniciou com a ideia de buscar as mensagens pelo id do usuario
     * 
     * Buscar as mensagens por id pode ser um problema meio complexo de se resolver, mas é necessário resolver isso antes de iniciar
     * o chat de fato, pois o chat é basicamente: Form -> Back-end -> SQL -> Back-end -> HTML Page.
     */
    @PostMapping("/procurarMensagem")
    public String mostrarMensagensPorId(@RequestParam String id) {
        System.out.println(id);
        return "/index";
    }
    
}