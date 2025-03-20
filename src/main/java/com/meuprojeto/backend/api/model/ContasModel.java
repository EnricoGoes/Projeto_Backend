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

    @Column(name = "Descricao_Conta", nullable = false)
    private String descricaoConta;

    @Column(name = "Valor_Conta", nullable = false)
    private Float valorConta;

    @Column(name = "DataVencimento_Conta", nullable = false)
    private LocalDate dataVencimentoConta;

    @Column(name = "DataPagamento_Conta")
    private LocalDate dataPagamentoConta;

    @ManyToOne
    @JoinColumn(name = "TipoConta_idCategoria", nullable = false)
    private CategoriaModel tipoConta;

    @Column(name = "Status_Conta", nullable = false)
    private Boolean statusConta;

    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", nullable = false)
    private UsuarioModel usuario;

}
