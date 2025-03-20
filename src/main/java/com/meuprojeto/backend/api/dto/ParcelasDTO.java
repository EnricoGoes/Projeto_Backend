package com.meuprojeto.backend.api.dto;

import com.meuprojeto.backend.api.model.ParcelasModel;
import com.meuprojeto.backend.api.model.ContasModel;
import com.meuprojeto.backend.api.repository.ContasRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParcelasDTO implements Serializable {
    private Long idParcelas;
    private LocalDate dataVencimentoParcela;
    private int numeroParcela;
    private double valorParcela;
    private Boolean statusParcela;
    private Long contaId;
    private String descricaoConta;

    public static ParcelasDTO converter(ParcelasModel parcela) {
        return new ParcelasDTO(
            parcela.getIdParcelas(),
            parcela.getDataVencimentoParcela(),
            parcela.getNumeroParcela(),
            parcela.getValorParcela(),
            parcela.getStatusParcela(),
            parcela.getConta() != null ? parcela.getConta().getIdContas() : null,
            parcela.getConta() != null ? parcela.getConta().getDescricaoConta() : null
        );
    }

    public static List<ParcelasDTO> converter(List<ParcelasModel> parcelas) {
        return parcelas.stream().map(ParcelasDTO::converter).collect(Collectors.toList());
    }

    public ParcelasModel toModel(ContasRepository contasRepository) {
        ParcelasModel parcela = new ParcelasModel();
        parcela.setDataVencimentoParcela(this.dataVencimentoParcela);
        parcela.setNumeroParcela(this.numeroParcela);
        parcela.setValorParcela(this.valorParcela);
        parcela.setStatusParcela(this.statusParcela);

        if (this.contaId != null) {
            Optional<ContasModel> conta = contasRepository.findById(this.contaId);
            conta.ifPresent(parcela::setConta);
        }

        return parcela;
    }
}
