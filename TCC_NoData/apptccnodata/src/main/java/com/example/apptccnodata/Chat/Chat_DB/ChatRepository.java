package com.example.apptccnodata.Chat.Chat_DB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatEntity, Long>{
    ChatEntity findByMensagem(String mensagem);
}
