package com.meuprojeto.backend.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuprojeto.backend.api.model.ParcelasModel;

@Repository
public interface ParcelasRepository extends JpaRepository<ParcelasModel, Long> {

    List<ParcelasModel> findByContaIdContas(Long id);

}