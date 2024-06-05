package br.com.estoqueapi.estoqueapi.controllers;

import br.com.estoqueapi.estoqueapi.dtos.categoria.CategoriasListDTO;
import br.com.estoqueapi.estoqueapi.dtos.produto.ProdutoDTO;
import br.com.estoqueapi.estoqueapi.entities.Produto;
import br.com.estoqueapi.estoqueapi.services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable String id) {
        return ResponseEntity.ok(produtoService.findById(id));
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Produto>> findByCategorias(@Valid @RequestBody CategoriasListDTO dto) {
        return ResponseEntity.ok(produtoService.findAllByCategoria(dto));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@Valid @RequestBody ProdutoDTO produto) {
        return new ResponseEntity<>(
                produtoService.save(produto),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> update(
            @PathVariable String id, @RequestBody ProdutoDTO produto) {

        return ResponseEntity.ok(produtoService.update(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
