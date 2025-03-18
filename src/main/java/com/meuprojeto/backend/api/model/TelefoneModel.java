package com.meuprojeto.backend.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Telefone")
public class TelefoneModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTelefone")
    private Long idTelefone;

    @Column(name = "Tipo_Telefone", nullable = false)
    private String tipoTelefone;
    
    @Column(name = "Numero_Telefone", nullable = false)
    private String numeroTelefone;

}
