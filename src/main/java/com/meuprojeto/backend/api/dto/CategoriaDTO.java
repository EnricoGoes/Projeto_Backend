package com.meuprojeto.backend.api.dto;

import com.meuprojeto.backend.api.model.CategoriaModel;

import lombok.Data;

@Data
public class CategoriaDTO {
    private Long id;
    private String descricao;
    private String tipo;

    public static CategoriaDTO converter(CategoriaModel categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getIdCategoria());
        dto.setDescricao(categoria.getDescricaoCategoria());
        dto.setTipo(categoria.getTipoCategoria());
        return dto;
    }

    public CategoriaModel toModel() {
        CategoriaModel categoriaModel = new CategoriaModel();
        categoriaModel.setDescricaoCategoria(this.descricao);
        categoriaModel.setTipoCategoria(this.tipo);
        return categoriaModel;
    }

}
