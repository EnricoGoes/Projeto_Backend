package com.meuprojeto.backend.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.dto.UsuarioDTO;
import com.meuprojeto.backend.api.model.EnderecoModel;
import com.meuprojeto.backend.api.model.TelefoneModel;
import com.meuprojeto.backend.api.model.UsuarioModel;
import com.meuprojeto.backend.api.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para adicionar um novo usuário
    @PostMapping
    public ResponseEntity<UsuarioModel> addUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioModel usuario = usuarioDTO.toModel();
        UsuarioModel savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    // Método para buscar usuário por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable Long id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para buscar por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<UsuarioModel> getUsuarioByNome(@PathVariable String nome) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByNomeUsuario(nome);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para buscar por cpf
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioModel> getUsuarioByCpf(@PathVariable String cpf) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByCpfUsuario(cpf);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para atualizar um usuário
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioDTO usuarioDTO) {

        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    if (usuarioDTO.getNome() != null) {
                        usuarioExistente.setNomeUsuario(usuarioDTO.getNome());
                    }
                    if (usuarioDTO.getCpf() != null) {
                        usuarioExistente.setCpfUsuario(usuarioDTO.getCpf());
                    }
                    if (usuarioDTO.getEmail() != null) {
                        usuarioExistente.setEmailUsuario(usuarioDTO.getEmail());
                    }
                    if (usuarioDTO.getLogin() != null) {
                        usuarioExistente.setLoginUsuario(usuarioDTO.getLogin());
                    }
                    if (usuarioDTO.getSenha() != null) {
                        usuarioExistente.setSenhaUsuario(usuarioDTO.getSenha());
                    }

                    if (usuarioDTO.getTelefone() != null) {
                        if (usuarioExistente.getTelefone() == null) {
                            usuarioExistente.setTelefone(new TelefoneModel());
                        }
                        if (usuarioDTO.getTelefone().getTipo() != null) {
                            usuarioExistente.getTelefone().setTipoTelefone(usuarioDTO.getTelefone().getTipo());
                        }
                        if (usuarioDTO.getTelefone().getNumero() != null) {
                            usuarioExistente.getTelefone().setNumeroTelefone(usuarioDTO.getTelefone().getNumero());
                        }
                    }

                    if (usuarioDTO.getEndereco() != null) {
                        if (usuarioExistente.getEndereco() == null) {
                            usuarioExistente.setEndereco(new EnderecoModel());
                        }
                        if (usuarioDTO.getEndereco().getRua() != null) {
                            usuarioExistente.getEndereco().setRuaEndereco(usuarioDTO.getEndereco().getRua());
                        }
                        if (usuarioDTO.getEndereco().getBairro() != null) {
                            usuarioExistente.getEndereco().setBairroEndereco(usuarioDTO.getEndereco().getBairro());
                        }
                        if (usuarioDTO.getEndereco().getNumero() != null) {
                            usuarioExistente.getEndereco().setNumeroEndereco(usuarioDTO.getEndereco().getNumero());
                        }
                        if (usuarioDTO.getEndereco().getCep() != null) {
                            usuarioExistente.getEndereco().setCepEndereco(usuarioDTO.getEndereco().getCep());
                        }
                    }

                    usuarioRepository.save(usuarioExistente);
                    return ResponseEntity.ok(UsuarioDTO.converter(usuarioExistente));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para deletar um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioModel> deleteUsuario(@PathVariable Long id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
