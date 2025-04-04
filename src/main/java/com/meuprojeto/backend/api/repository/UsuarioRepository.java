package com.meuprojeto.backend.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuprojeto.backend.api.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByLoginUsuarioAndSenhaUsuario(String loginUsuario, String senhaUsuario);
    Optional<UsuarioModel> findByNomeUsuario(String nomeUsuario);
    Optional<UsuarioModel> findByCpfUsuario(String cpf);
    
}