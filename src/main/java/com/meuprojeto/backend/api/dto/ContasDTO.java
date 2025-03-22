package com.meuprojeto.backend.api.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.meuprojeto.backend.api.model.CategoriaModel;
import com.meuprojeto.backend.api.model.ContasModel;
import com.meuprojeto.backend.api.model.UsuarioModel;
import com.meuprojeto.backend.api.repository.CategoriaRepository;
import com.meuprojeto.backend.api.repository.UsuarioRepository;

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
    private Boolean status;
    private Long usuarioId;
    private Long categoriaId;
    private String nomeUsuario;
    private String nomeCategoria;

    public static ContasDTO converter(ContasModel contasModel) {
        return new ContasDTO(
            contasModel.getIdContas(),
            contasModel.getDescricaoConta(),
            contasModel.getValorConta(),
            contasModel.getDataVencimentoConta(),
            contasModel.getDataPagamentoConta(),
            contasModel.getStatusConta(),
            contasModel.getUsuario() != null ? contasModel.getUsuario().getIdUsuario() : null,
            contasModel.getTipoConta() != null ? contasModel.getTipoConta().getIdCategoria() : null,
            contasModel.getUsuario() != null ? contasModel.getUsuario().getNomeUsuario() : null,
            contasModel.getTipoConta() != null ? contasModel.getTipoConta().getDescricaoCategoria() : null
        );
    }

    public static List<ContasDTO> converter(List<ContasModel> contas) {
        return contas.stream().map(ContasDTO::converter).collect(Collectors.toList());
    }

    public ContasModel toModel(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        ContasModel contas = new ContasModel();
        contas.setDescricaoConta(this.descricao);
        contas.setValorConta(this.valor);
        contas.setDataVencimentoConta(this.dataVencimento);
        contas.setDataPagamentoConta(this.dataPagamento);
        contas.setStatusConta(this.status);

        if (this.usuarioId != null) {
            UsuarioModel usuario = usuarioRepository.findById(this.usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + this.usuarioId));
            contas.setUsuario(usuario);
        }

        if (this.categoriaId != null) {
            CategoriaModel categoria = categoriaRepository.findById(this.categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + this.categoriaId));
            contas.setTipoConta(categoria);
        }

        return contas;
    }
}
