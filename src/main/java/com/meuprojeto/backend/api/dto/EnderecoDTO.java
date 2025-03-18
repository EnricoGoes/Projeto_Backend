package com.meuprojeto.backend.api.dto;

import com.meuprojeto.backend.api.model.EnderecoModel;

import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;
    private String rua;
    private String bairro;
    private String numero;
    private String cep;

    public static EnderecoDTO converter(EnderecoModel endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getIdEndereco());
        dto.setRua(endereco.getRuaEndereco());
        dto.setBairro(endereco.getBairroEndereco());
        dto.setNumero(endereco.getNumeroEndereco());
        dto.setCep(endereco.getCepEndereco());
        return dto;
    }

}
