package com.meuprojeto.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.dto.TelefoneDTO;
import com.meuprojeto.backend.api.model.TelefoneModel;
import com.meuprojeto.backend.api.repository.TelefoneRepository;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneRepository telefoneRepository;

    // Método para criar um novo telefone
    @PostMapping
    public ResponseEntity<TelefoneModel> addTelefone(@RequestBody TelefoneDTO telefoneDTO) {
        TelefoneModel telefone = telefoneDTO.toModel();
        TelefoneModel savedTelefone = telefoneRepository.save(telefone);
        return ResponseEntity.ok(savedTelefone);
    }
    
    // Método parar atualizar um telefone
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@PathVariable Long id,
            @RequestBody TelefoneDTO telefoneAtualizado) {
        return telefoneRepository.findById(id)
                .map(telefoneExistente -> {
                    if (telefoneAtualizado.getNumero() != null) {
                        telefoneExistente.setNumeroTelefone(telefoneAtualizado.getNumero());
                    }
                    if (telefoneAtualizado.getTipo() != null) {
                        telefoneExistente.setTipoTelefone(telefoneAtualizado.getTipo());
                    }

                    TelefoneModel telefoneSalvo = telefoneRepository.save(telefoneExistente);
                    return ResponseEntity.ok(TelefoneDTO.converter(telefoneSalvo));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para deletar um telefone
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTelefone(@PathVariable Long id) {
        return telefoneRepository.findById(id)
                .map(telefone -> {
                    telefoneRepository.delete(telefone);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
