package com.meuprojeto.backend.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meuprojeto.backend.api.model.EnderecoModel;
import com.meuprojeto.backend.api.model.UsuarioModel;
import com.meuprojeto.backend.api.repository.EnderecoRepository;
import com.meuprojeto.backend.api.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public UsuarioModel criarUsuario(UsuarioModel usuario) {
        if (usuario.getEndereco().getIdEndereco() != null) {
            EnderecoModel enderecoExistente = enderecoRepository.findById(
                usuario.getEndereco().getIdEndereco()
            ).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

            usuario.setEndereco(enderecoExistente);
        }

        return usuarioRepository.save(usuario);

    }

}
