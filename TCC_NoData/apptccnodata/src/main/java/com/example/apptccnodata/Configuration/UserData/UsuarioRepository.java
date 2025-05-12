package com.example.apptccnodata.Configuration.UserData;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    // Atenção com o Optional ou Usuario, pois usando Optional nunca retorna null já que o mesmo nunca é nulo

    // O possível erro para o salvamento era estar usando o Optional mas sem utilizar explicitamente a outra clausa da opção
    Usuario findByEmail(String email);
}
