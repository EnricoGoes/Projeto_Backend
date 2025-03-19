package com.meuprojeto.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.dto.TelefoneDTO;
import com.meuprojeto.backend.api.model.TelefoneModel;
import com.meuprojeto.backend.api.repository.TelefoneRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneRepository telefoneRepository;

    //Método parar atualizar um telefone
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> atualizarTelefone(@PathVariable Long id, @RequestBody TelefoneDTO telefoneAtualizado) {
        return telefoneRepository.findById(id)
            .map(telefoneExistente -> {
                telefoneExistente.setNumeroTelefone(telefoneAtualizado.getNumero());
                telefoneExistente.setTipoTelefone(telefoneAtualizado.getTipo());

                TelefoneModel telefoneSalvo = telefoneRepository.save(telefoneExistente);
                return ResponseEntity.ok(TelefoneDTO.converter(telefoneSalvo));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
