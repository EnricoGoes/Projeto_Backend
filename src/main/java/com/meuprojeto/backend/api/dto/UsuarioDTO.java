package com.meuprojeto.backend.api.dto;

import java.io.Serializable;

import com.meuprojeto.backend.api.model.UsuarioModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO implements Serializable {
    private Long id;
    private String nome;
    private String cpf;
    private TelefoneDTO telefone;
    private EnderecoDTO endereco;
    private String email;
    private String login;
    private String senha;

    public static UsuarioDTO converter(UsuarioModel usuarioModel){
        return new UsuarioDTO(
            usuarioModel.getIdUsuario(),
            usuarioModel.getNomeUsuario(),
            usuarioModel.getCpfUsuario(),
            usuarioModel.getTelefone() != null ? TelefoneDTO.converter(usuarioModel.getTelefone()) : null,
            usuarioModel.getEndereco() != null ? EnderecoDTO.converter(usuarioModel.getEndereco()) : null,
            usuarioModel.getEmailUsuario(),
            usuarioModel.getLoginUsuario(),
            usuarioModel.getSenhaUsuario()
        );
    }

    public UsuarioModel toModel() {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNomeUsuario(this.nome);
        usuario.setCpfUsuario(this.cpf);
        usuario.setEmailUsuario(this.email);
        usuario.setLoginUsuario(this.login);
        usuario.setSenhaUsuario(this.senha);
        if (this.telefone != null) {
            usuario.setTelefone(this.telefone.toModel());
        }
        if (this.endereco != null) {
            usuario.setEndereco(this.endereco.toModel());
        }
        return usuario;
    }

}
