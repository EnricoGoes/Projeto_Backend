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
@Table(name = "categoria")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Long idCategoria;
    
    @Column(name = "Descricao_Categoria", nullable = false)
    private String descricaoCategoria;
    
    @Column(name = "Tipo_Categoria", nullable = false)
    private String tipoCategoria;

}
