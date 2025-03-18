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
@Table(name = "Parcelas")
public class ParcelasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idParcelas")
    private Long idParcelas;

    @Column(nullable = false)
    private LocalDate DataVencimento_Parcela;

    @Column(nullable = false)
    private int Numero_Parcela;

    @Column(nullable = false)
    private double Valor_Parcela;

    @Column(nullable = false)
    private String Status_Parcela;

    @ManyToOne
    @JoinColumn(name = "Contas_idContas", nullable = false)
    private ContasModel Conta;

    public ParcelasModel(Long idParcelas, LocalDate dataVencimento_Parcela, int numero_Parcela, double valor_Parcela,
            String status_Parcela, ContasModel conta) {
        this.idParcelas = idParcelas;
        DataVencimento_Parcela = dataVencimento_Parcela;
        Numero_Parcela = numero_Parcela;
        Valor_Parcela = valor_Parcela;
        Status_Parcela = status_Parcela;
        Conta = conta;
    }

    public ParcelasModel() {
    }

}
