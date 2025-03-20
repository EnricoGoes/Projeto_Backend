package com.meuprojeto.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meuprojeto.backend.api.dto.ParcelasDTO;
import com.meuprojeto.backend.api.model.ParcelasModel;
import com.meuprojeto.backend.api.repository.ContasRepository;
import com.meuprojeto.backend.api.repository.ParcelasRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/parcelas")
public class ParcelasController {

    @Autowired
    private ParcelasRepository parcelasRepository;

    @Autowired
    private ContasRepository contasRepository;

    // Método para criar parcelas
    @PostMapping
    public ResponseEntity<ParcelasModel> addParcelas(@RequestBody ParcelasDTO parcelasDTO) {
        ParcelasModel parcelas = parcelasDTO.toModel(contasRepository);
        ParcelasModel savedParcelas = parcelasRepository.save(parcelas);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParcelas);
    }

    // Método para listar todas as parcelas
    @GetMapping("/listall")
    public ResponseEntity<List<ParcelasDTO>> listAll() {
        List<ParcelasModel> parcelas = parcelasRepository.findAll();
        List<ParcelasDTO> parcelasDTO = ParcelasDTO.converter(parcelas);
        return ResponseEntity.ok(parcelasDTO);
    }

    // Método para buscar parcela por id
    @GetMapping("/{id}")
    public ResponseEntity<ParcelasDTO> getParcelaById(@PathVariable Long id) {
        return parcelasRepository.findById(id)
                .map(parcela -> ResponseEntity.ok(ParcelasDTO.converter(parcela)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para atualizar parcela
    @PutMapping("/{id}")
    public ResponseEntity<ParcelasDTO> atualizarParcela(@PathVariable Long id, @RequestBody ParcelasDTO parcelaDTO) {
        return parcelasRepository.findById(id)
                .map(parcelaExistente -> {
                    if (parcelaDTO.getDataVencimentoParcela() != null) {
                        parcelaExistente.setDataVencimentoParcela(parcelaDTO.getDataVencimentoParcela());
                    }
                    if (parcelaDTO.getNumeroParcela() != 0) {
                        parcelaExistente.setNumeroParcela(parcelaDTO.getNumeroParcela());
                    }
                    if (parcelaDTO.getValorParcela() != 0) {
                        parcelaExistente.setValorParcela(parcelaDTO.getValorParcela());
                    }
                    if (parcelaDTO.getStatusParcela() != null) {
                        parcelaExistente.setStatusParcela(parcelaDTO.getStatusParcela());
                    }

                    ParcelasModel parcelaAtualizada = parcelasRepository.save(parcelaExistente);
                    return ResponseEntity.ok(ParcelasDTO.converter(parcelaAtualizada));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para deletar parcela
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParcela(@PathVariable Long id) {
        return parcelasRepository.findById(id)
                .map(parcela -> {
                    parcelasRepository.delete(parcela);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
