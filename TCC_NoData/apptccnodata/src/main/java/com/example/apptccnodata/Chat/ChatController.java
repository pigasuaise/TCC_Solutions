package com.example.apptccnodata.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.apptccnodata.Chat.Chat_DB.ChatEntity;
import com.example.apptccnodata.Chat.Chat_DB.ChatRepository;
import com.example.apptccnodata.Configuration.UserData.Usuario;
import com.example.apptccnodata.SessionController.SessionController;

import jakarta.servlet.http.HttpSession;



@Controller
public class ChatController {
    @Autowired
    private SessionController sController;

    @Autowired
    private ChatRepository cr;

    @GetMapping("/chat")
    public String showChat(HttpSession session) {
        // Validação se o usuário está logado pelo addAttribute do login
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/index";
        }
        return "chat";
    }
    
    @PostMapping("/enviarMensagem")
    public String postMethodName(@RequestParam String mensagem, ChatEntity cEntity, HttpSession session) {
        //TODO: process POST request
        Usuario usuario = sController.getLoggedUser();

        if (usuario != null) {
            System.out.println(usuario.getNome() + ": " + mensagem);
        } else {
            System.out.println("Usuario não foi encontrado!");
        }
        
        return "redirect:/chat";
    }
    
}
