package com.meuprojeto.backend.api.controller;

import com.meuprojeto.backend.api.dto.ContasDTO;
import com.meuprojeto.backend.api.model.CategoriaModel;
import com.meuprojeto.backend.api.model.ContasModel;
import com.meuprojeto.backend.api.repository.CategoriaRepository;
import com.meuprojeto.backend.api.repository.ContasRepository;
import com.meuprojeto.backend.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/contas")
public class ContasController {

    @Autowired
    private ContasRepository contasRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para criar uma nova conta
    @PostMapping
    public ResponseEntity<ContasModel> addConta(@RequestBody ContasDTO contasDTO) {
        ContasModel contas = contasDTO.toModel(categoriaRepository, usuarioRepository);
        ContasModel savedContas = contasRepository.save(contas);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContas);
    }

    // Método para buscar todas as contas
    @GetMapping("/listall")
    public ResponseEntity<List<ContasDTO>> listAll() {
        List<ContasModel> contas = contasRepository.findAll();
        List<ContasDTO> contasDTO = ContasDTO.converter(contas);
        return ResponseEntity.ok(contasDTO);
    }

    // Método para buscar conta por id
    @GetMapping("/{id}")
    public ResponseEntity<ContasDTO> getContaById(@PathVariable Long id) {
        Optional<ContasModel> conta = contasRepository.findById(id);
        if (conta.isPresent()) {
            ContasDTO contaDTO = ContasDTO.converter(conta.get());
            return ResponseEntity.ok(contaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para buscar contas pelo status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContasDTO>> getContaByStatus(@PathVariable Boolean status) {
        List<ContasModel> contas = contasRepository.findByStatusConta(status);
        if (!contas.isEmpty()) {
            List<ContasDTO> contasDTO = ContasDTO.converter(contas);
            return ResponseEntity.ok(contasDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para buscar pelo nome do usuário
    @GetMapping("/usuario/{nome}")
    public ResponseEntity<List<ContasDTO>> getContaByUsuario(@PathVariable String nome) {
        List<ContasModel> contas = contasRepository.findByUsuario_NomeUsuario(nome);
        if (!contas.isEmpty()) {
            List<ContasDTO> contasDTO = ContasDTO.converter(contas);
            return ResponseEntity.ok(contasDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para atualizar uma conta
    @PutMapping("/{id}")
    public ResponseEntity<ContasDTO> atualizarConta(@PathVariable Long id, @RequestBody ContasDTO contaDTO) {
        return contasRepository.findById(id)
                .map(contaExistente -> {
                    if (contaDTO.getDescricao() != null) {
                        contaExistente.setDescricaoConta(contaDTO.getDescricao());
                    }
                    if (contaDTO.getValor() != null) {
                        contaExistente.setValorConta(contaDTO.getValor());
                    }
                    if (contaDTO.getDataVencimento() != null) {
                        contaExistente.setDataVencimentoConta(contaDTO.getDataVencimento());
                    }
                    if (contaDTO.getDataPagamento() != null) {
                        contaExistente.setDataPagamentoConta(contaDTO.getDataPagamento());
                    }
                    if (contaDTO.getCategoriaId() != null) {
                        Optional<CategoriaModel> categoria = categoriaRepository.findById(contaDTO.getCategoriaId());
                        categoria.ifPresent(contaExistente::setTipoConta);
                    }
                    if (contaDTO.getStatus() != null) {
                        contaExistente.setStatusConta(contaDTO.getStatus());
                    }

                    ContasModel contaSalva = contasRepository.save(contaExistente);
                    return ResponseEntity.ok(ContasDTO.converter(contaSalva));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para deletar uma conta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConta(@PathVariable Long id) {
        return contasRepository.findById(id)
                .map(conta -> {
                    contasRepository.delete(conta);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}