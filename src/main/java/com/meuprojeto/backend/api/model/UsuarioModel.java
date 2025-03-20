package com.meuprojeto.backend.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "Nome_Usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "CPF_Usuario",nullable = false, unique = true)
    private String cpfUsuario;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Telefone_idTelefone", referencedColumnName = "idTelefone", unique = true)
    private TelefoneModel telefone;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Endereco_idEndereco", referencedColumnName = "idEndereco")
    private EnderecoModel endereco;
    
    @Column(name = "Email_Usuario", nullable = false, unique = true)
    private String emailUsuario;

    @Column(name = "Login_Usuario", nullable = false, unique = true)
    private String loginUsuario;

    @Column(name = "Senha_Usuario", nullable = false)
    private String senhaUsuario;

}
