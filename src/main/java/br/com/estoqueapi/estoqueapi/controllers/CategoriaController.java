package br.com.estoqueapi.estoqueapi.controllers;

import br.com.estoqueapi.estoqueapi.dtos.categoria.CategoriaDTO;
import br.com.estoqueapi.estoqueapi.entities.Categoria;
import br.com.estoqueapi.estoqueapi.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable String id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @GetMapping("/categoria/{nome}")
    public ResponseEntity<Categoria> findByNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaService.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@Valid @RequestBody CategoriaDTO categoria) {
        return new ResponseEntity<>(
                categoriaService.save(categoria),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> update(
            @PathVariable String id, @RequestBody CategoriaDTO categoria) {

        return ResponseEntity.ok(categoriaService.update(id, categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
