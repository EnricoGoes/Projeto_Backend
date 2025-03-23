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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/auth")
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para realizar login com verificação de credenciais
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login, HttpServletResponse response) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByLoginUsuarioAndSenhaUsuario(
                login.getLogin(), login.getSenha());

        if (usuario.isPresent()) {
            Cookie cookie = new Cookie("userId", String.valueOf(usuario.get().getIdUsuario()));
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok(String.valueOf(usuario.get().getIdUsuario()));
                                                                                   
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}
