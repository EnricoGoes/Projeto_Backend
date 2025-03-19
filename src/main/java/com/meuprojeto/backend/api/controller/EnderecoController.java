package com.meuprojeto.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

    // Método para atualizar um endereço
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoAtualizado) {
        return enderecoRepository.findById(id)
            .map(enderecoExistente -> {
                enderecoExistente.setRuaEndereco(enderecoAtualizado.getRua());
                enderecoExistente.setBairroEndereco(enderecoAtualizado.getBairro());
                enderecoExistente.setNumeroEndereco(enderecoAtualizado.getNumero());
                enderecoExistente.setCepEndereco(enderecoAtualizado.getCep());

                EnderecoModel enderecoSalvo = enderecoRepository.save(enderecoExistente);
                return ResponseEntity.ok(EnderecoDTO.converter(enderecoSalvo));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
