package com.meuprojeto.backend.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.model.UsuarioModel;
import com.meuprojeto.backend.api.repository.EnderecoRepository;
import com.meuprojeto.backend.api.repository.TelefoneRepository;
import com.meuprojeto.backend.api.repository.UsuarioRepository;
import com.meuprojeto.backend.api.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private UsuarioService usuarioService;

    // Método para adicionar um novo usuário
    @PostMapping
    public ResponseEntity<UsuarioModel> addLivro(@RequestBody UsuarioModel usuario) {
        UsuarioModel savedUsuario = usuarioService.criarUsuario(usuario);
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

    // Método para atualizar um usuário
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioModel usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
                    usuarioExistente.setCpfUsuario(usuarioAtualizado.getCpfUsuario());
                    usuarioExistente.setEmailUsuario(usuarioAtualizado.getEmailUsuario());
                    usuarioExistente.setLoginUsuario(usuarioAtualizado.getLoginUsuario());
                    usuarioExistente.setSenhaUsuario(usuarioAtualizado.getSenhaUsuario());
                    
                    if (usuarioAtualizado.getTelefone() != null) {
                        usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
                    }
            
                    // Se o JSON não enviar endereço, mantém o endereço existente
                    if (usuarioAtualizado.getEndereco() != null) {
                        usuarioExistente.setEndereco(usuarioAtualizado.getEndereco());
                    }

                    UsuarioModel usuarioSalvo = usuarioRepository.save(usuarioExistente);
                    return ResponseEntity.ok(usuarioSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
