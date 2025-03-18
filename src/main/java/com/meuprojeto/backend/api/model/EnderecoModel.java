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
@Table(name = "Endereco")
public class EnderecoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEndereco")
    private Long idEndereco;
   
    @Column(name = "Rua_Endereco", nullable = false)
    private String ruaEndereco;
    
    @Column(name = "Bairro_Endereco", nullable = false)
    private String bairroEndereco;
   
    @Column(name = "Numero_Endereco", nullable = false)
    private String numeroEndereco;
   
    @Column(name = "Cep_Endereco", nullable = false)
    private String cepEndereco;

}
