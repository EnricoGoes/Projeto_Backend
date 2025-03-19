package com.meuprojeto.backend.api.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.dto.LoginDTO;
import com.meuprojeto.backend.api.model.UsuarioModel;
import com.meuprojeto.backend.api.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/auth")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Método para realizar login com verificação de credenciais
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByLoginUsuarioAndSenhaUsuario(
            login.getLogin(), login.getSenha()
        );

        if (usuario.isPresent()) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}
