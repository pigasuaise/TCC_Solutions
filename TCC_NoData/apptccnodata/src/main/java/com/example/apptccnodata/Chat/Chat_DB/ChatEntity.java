package com.example.apptccnodata.Chat.Chat_DB;

import com.example.apptccnodata.Configuration.UserData.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

/*
 * Classe para criar os campos do banco de dados.
 * 
 * Nesta classe existe uma joint, é necessário fazer isso para coletar o id do usuario que mandou a mensagem
 * um exemplo de uso seria para consultar algo dentro do banco de dados
 * 
 * Por mais que não exista a necessidade de utilizar isso, ainda seria preciso definir quem é o remetente e o destinatário
 * No momento esta classe é utilizada para consultas, mas futuramente para linkar usuario para cada chat
 * 
 * Comunicação de A -> B e B <- A.
 */
@Entity
@Table(name="mensagens")
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable = false)
    private Usuario usuario;

    public ChatEntity(String mensagem, Usuario usuario) {
        this.mensagem = mensagem;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
