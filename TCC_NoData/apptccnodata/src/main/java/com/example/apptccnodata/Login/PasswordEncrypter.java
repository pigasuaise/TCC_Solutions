package com.example.apptccnodata.Login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * Esta classe é de java puro, então não precisa de anotações. Normalmente, seria de fato interessante utilizar
 * uma anotação de Bean aqui, mas como esta classe serve apenas, e exclusivamente, para o login e registro seria inutil
 * deixa-lo gerenciado pelo Spring, assim evita peso de memória.
 */

/*
 * Quando este método é chamado, ele exige, por regras de negócio, que seja inserido um
 * parâmetro dentro, que neste caso é sempre a senha do usuário.
 * 
 * Esta classe também pode ser utilizada para descriptografar algum hash, mas como geralmente
 * ela é privada nas importações, ficamos limitados à apenas oque está escrito.
 */
public class PasswordEncrypter {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String hash(String senha) {
        return passwordEncoder.encode(senha);
    }

}

