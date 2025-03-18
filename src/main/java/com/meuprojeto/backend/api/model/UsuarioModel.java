package com.meuprojeto.backend.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "livro")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String Nome_Usuario;

    @Column(nullable = false, unique = true)
    private String CPF_Usuario;
    
    @Column(nullable = false)
    private String Telefone_Usuario;
    
    @ManyToOne
    @JoinColumn(name = "Endereco_idEndereco", nullable = false)
    private EnderecoModel Endereco;
    
    @Column(nullable = false, unique = true)
    private String Email_Usuario;

    @Column(nullable = false, unique = true)
    private String Login_Usuario;

    @Column(nullable = false)
    private String Senha_Usuario;

    public UsuarioModel(Long idUsuario, String nome_Usuario, String cPF_Usuario, String telefone_Usuario,
            EnderecoModel endereco, String email_Usuario, String login_Usuario, String senha_Usuario) {
        this.idUsuario = idUsuario;
        Nome_Usuario = nome_Usuario;
        CPF_Usuario = cPF_Usuario;
        Telefone_Usuario = telefone_Usuario;
        Endereco = endereco;
        Email_Usuario = email_Usuario;
        Login_Usuario = login_Usuario;
        Senha_Usuario = senha_Usuario;
    }

    public UsuarioModel() {
    }

}
