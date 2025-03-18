package com.meuprojeto.backend.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.meuprojeto.backend.api.model.ParcelasModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParcelasDTO implements Serializable {
    private Long id;
    private LocalDate dataVencimento;
    private int numero;
    private double valor;
    private String status;
    private String Conta;

    public static ParcelasDTO converter(ParcelasModel parcelasModel){
        return new ParcelasDTO(
            parcelasModel.getIdParcelas(),
            parcelasModel.getDataVencimentoParcela(),
            parcelasModel.getNumeroParcela(),
            parcelasModel.getValorParcela(),
            parcelasModel.getStatusParcela(),
            parcelasModel.getConta() != null ? parcelasModel.getConta().getDescricaoConta() : null
        );
    }

    public static List<ParcelasDTO> converter(List<ParcelasModel> parcelas) {
        return parcelas.stream().map(ParcelasDTO::converter).collect(Collectors.toList());
    }

}
