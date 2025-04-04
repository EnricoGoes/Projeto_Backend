package com.meuprojeto.backend.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meuprojeto.backend.api.model.TelefoneModel;

@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneModel, Long> {

}
