package com.meuprojeto.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.dto.CategoriaDTO;
import com.meuprojeto.backend.api.model.CategoriaModel;
import com.meuprojeto.backend.api.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // Método para criar uma nova categoria
    @PostMapping
    public ResponseEntity<CategoriaModel> addCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaModel categoria = categoriaDTO.toModel();
        CategoriaModel savedCategoria = categoriaRepository.save(categoria);
        return ResponseEntity.ok(savedCategoria);
    }

    // Método para listar todas as categorias
    @GetMapping("/listall")
    public ResponseEntity<Iterable<CategoriaModel>> listAll() {
        Iterable<CategoriaModel> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }
    
    // Método para buscar uma categoria pelo id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaModel> getCategoriaById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para atualizar uma categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaModel> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaRepository.findById(id)
                .map(categoriaExistente -> {
                    if (categoriaDTO.getDescricao() != null) {
                        categoriaExistente.setDescricaoCategoria(categoriaDTO.getDescricao());
                    }
                    if (categoriaDTO.getTipo() != null) {
                        categoriaExistente.setTipoCategoria(categoriaDTO.getTipo());
                    }
                    CategoriaModel categoriaAtualizada = categoriaRepository.save(categoriaExistente);
                    return ResponseEntity.ok(categoriaAtualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Método para deletar uma categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoriaRepository.delete(categoria);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
