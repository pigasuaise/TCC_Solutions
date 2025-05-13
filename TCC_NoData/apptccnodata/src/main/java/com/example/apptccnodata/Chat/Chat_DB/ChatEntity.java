package com.example.apptccnodata.Chat.Chat_DB;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.example.apptccnodata.Configuration.UserData.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="mensagens")
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String mensagem;

    @NotEmpty
    private String usuarioNome;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuarioId;

    public ChatEntity(){}

    public ChatEntity(String mensagem, Usuario usuarioId) {
        this.mensagem = mensagem;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }
    public String getUsuario() {
        return usuarioNome;
    }

    public void setUsuario(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
