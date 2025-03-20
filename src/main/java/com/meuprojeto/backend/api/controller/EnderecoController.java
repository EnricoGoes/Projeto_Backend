package com.meuprojeto.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.dto.EnderecoDTO;
import com.meuprojeto.backend.api.model.EnderecoModel;
import com.meuprojeto.backend.api.repository.EnderecoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    //Método para criar endereco
    @PostMapping
    public ResponseEntity<EnderecoModel> addEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        EnderecoModel endereco = enderecoDTO.toModel();
        EnderecoModel savedEndereco = enderecoRepository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEndereco);
    }

    //Método para atualizar um endereço
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(
            @PathVariable Long id,
            @RequestBody EnderecoDTO enderecoAtualizado) {
        return enderecoRepository.findById(id)
                .map(enderecoExistente -> {
                    // Atualiza apenas os campos fornecidos no DTO
                    if (enderecoAtualizado.getRua() != null) {
                        enderecoExistente.setRuaEndereco(enderecoAtualizado.getRua());
                    }
                    if (enderecoAtualizado.getBairro() != null) {
                        enderecoExistente.setBairroEndereco(enderecoAtualizado.getBairro());
                    }
                    if (enderecoAtualizado.getNumero() != null) {
                        enderecoExistente.setNumeroEndereco(enderecoAtualizado.getNumero());
                    }
                    if (enderecoAtualizado.getCep() != null) {
                        enderecoExistente.setCepEndereco(enderecoAtualizado.getCep());
                    }

                    EnderecoModel enderecoSalvo = enderecoRepository.save(enderecoExistente);
                    return ResponseEntity.ok(EnderecoDTO.converter(enderecoSalvo));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para atualizar um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEndereco(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    enderecoRepository.delete(endereco);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
