package com.meuprojeto.backend.api.model;

import java.time.LocalDate;

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
@Table(name = "Contas")
public class ContasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContas")
    private Long idContas;

    @Column(nullable = false)
    private String Descricao_Conta;

    @Column(nullable = false)
    private Float Valor_Conta;

    @Column(nullable = false)
    private LocalDate DataVencimento_Conta;

    @Column(nullable = false)
    private LocalDate DataPagamento_Conta;

    @Column(nullable = false)

    @ManyToOne
    @JoinColumn(name = "TipoConta_idCategoria", nullable = false)
    private CategoriaModel TipoConta;

    @Column(nullable = false)
    private Boolean Status_Conta;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false, unique = true)
    private UsuarioModel Usuario;

    public ContasModel(Long idContas, String descricao_Conta, Float valor_Conta, LocalDate dataVencimento_Conta,
            LocalDate dataPagamento_Conta, CategoriaModel tipoConta, Boolean status_Conta, UsuarioModel usuario) {
        this.idContas = idContas;
        Descricao_Conta = descricao_Conta;
        Valor_Conta = valor_Conta;
        DataVencimento_Conta = dataVencimento_Conta;
        DataPagamento_Conta = dataPagamento_Conta;
        TipoConta = tipoConta;
        Status_Conta = status_Conta;
        Usuario = usuario;
    }

    public ContasModel() {
    }

}
