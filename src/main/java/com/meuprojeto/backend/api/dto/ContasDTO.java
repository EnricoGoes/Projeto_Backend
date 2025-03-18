package com.meuprojeto.backend.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.meuprojeto.backend.api.model.ContasModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContasDTO implements Serializable {
    private Long id;
    private String descricao;
    private Float valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private CategoriaDTO tipo;
    private Boolean status;
    private String Usuario;

    public static ContasDTO converter(ContasModel contasModel){
        return new ContasDTO(
            contasModel.getIdContas(),
            contasModel.getDescricaoConta(),
            contasModel.getValorConta(),
            contasModel.getDataVencimentoConta(),
            contasModel.getDataPagamentoConta(),
            contasModel.getTipoConta() != null ? CategoriaDTO.converter(contasModel.getTipoConta()) : null,
            contasModel.getStatusConta(),
            contasModel.getUsuario() != null? contasModel.getUsuario().getNomeUsuario() : null
        );
    }

    public static List<ContasDTO> converter(List<ContasModel> contas) {
        return contas.stream().map(ContasDTO::converter).collect(Collectors.toList());
    }

}
