package com.example.apptccnodata.Login.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apptccnodata.Configuration.UserData.Usuario;
import com.example.apptccnodata.Configuration.UserData.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Por mais que o Spring indique que @Autowired é inútil, precisar ter esta anotação
    // para utilizar tanto repositório do usuário quanto do chat.
    @Autowired
    public LoginService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /* 
    Método para a validação do usuário, busca pela senha inicial (a senha que está no formulário de login) e 
    compara com o Hash do banco de dados.

    Está é uma classe bastante sensível, já que lida diretamente com os dados armazenados.
    Cuidar dela é necessário, então quanto mais segurança melhor

    Uma boa prática seria ofuscar este código, mas por enquanto ainda estamos trabalhando localmente
    assim evitando erros quanto ao SQL Injection ou Rainbow Tables.
    */
    public Usuario authenticate(String email, String rawSenha) {
        // Aqui utiliza-se do método dentro de usuarioRepository
        Usuario usuario = usuarioRepository.findByEmail(email);

        // Então, se usuario não for nulo e a senha bata com a senha do banco de dados, o usuário será logado.
        if(usuario != null && passwordEncoder.matches(rawSenha, usuario.getSenha())) {
            return usuario;
        }
        return null; // e como temos apenas uma opção lógica, apenas digitamos abaixo que o ultimo retorno vai ser null.
    }
}
