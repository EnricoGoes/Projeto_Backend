package com.meuprojeto.backend.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuprojeto.backend.api.model.ContasModel;

@Repository
public interface ContasRepository extends JpaRepository<ContasModel, Long> {

    Optional<ContasModel> findByTipoConta_DescricaoCategoria(String descricaoCategoria);
    List<ContasModel> findByStatusConta(Boolean statusConta);
    List<ContasModel> findByUsuario_NomeUsuario(String nome);


}
