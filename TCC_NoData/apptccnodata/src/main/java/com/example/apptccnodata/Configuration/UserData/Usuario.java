package com.example.apptccnodata.Configuration.UserData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/*
 * Classe para criar os campos das tabelas no banco de dados.
 */
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;
    @NotEmpty
    private String senha;
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;
    @NotEmpty
    @Column(unique = true)
    private String cpf;


    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
