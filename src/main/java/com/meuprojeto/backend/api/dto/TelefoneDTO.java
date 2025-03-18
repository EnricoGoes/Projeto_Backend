package com.meuprojeto.backend.api.dto;

import com.meuprojeto.backend.api.model.TelefoneModel;

import lombok.Data;

@Data
public class TelefoneDTO {
    private Long id;
    private String tipo;
    private String numero;

    public static TelefoneDTO converter(TelefoneModel telefone) {
        TelefoneDTO dto = new TelefoneDTO();
        dto.setId(telefone.getIdTelefone());
        dto.setTipo(telefone.getTipoTelefone());
        dto.setNumero(telefone.getNumeroTelefone());
        return dto;
    }

}
