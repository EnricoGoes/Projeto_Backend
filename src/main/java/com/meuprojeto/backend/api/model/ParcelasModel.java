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

    @Column(name = "DataVencimento_Parcela", nullable = false)
    private LocalDate dataVencimentoParcela;

    @Column(name = "Numero_Parcela", nullable = false)
    private int numeroParcela;

    @Column(name = "Valor_Parcela", nullable = false)
    private double valorParcela;

    @Column(name = "Status_Parcela", nullable = false)
    private Boolean statusParcela;

    @ManyToOne
    @JoinColumn(name = "Conta_idContas", nullable = false)
    private ContasModel conta;

}
