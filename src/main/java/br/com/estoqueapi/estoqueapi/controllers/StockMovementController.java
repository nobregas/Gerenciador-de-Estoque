package br.com.estoqueapi.estoqueapi.controllers;

import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockEntryMovementDTO;
import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockLeaveMovementDTO;
import br.com.estoqueapi.estoqueapi.entities.StockMovement;
import br.com.estoqueapi.estoqueapi.entities.enums.MovementType;
import br.com.estoqueapi.estoqueapi.services.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @PostMapping("/entrada")
    public ResponseEntity<StockMovement> registrarEntrada(
            @Valid @RequestBody StockEntryMovementDTO dto) {

        return new ResponseEntity<>(stockMovementService.registrarEntrada(dto),
                HttpStatus.CREATED);
    }

    @PostMapping("/saida")
    public ResponseEntity<StockMovement> registrarSaida(
            @Valid @RequestBody StockLeaveMovementDTO dto) {

        return new ResponseEntity<>(stockMovementService.registrarSaida(dto),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StockMovement>> listar() {
        return ResponseEntity.ok(stockMovementService.obterTodasMovimentacoes());
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<List<StockMovement>> obterPorId(@PathVariable String id) {
        return ResponseEntity.ok(stockMovementService.obterTodasMovimentacoesDoProdutoId(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<StockMovement>> obterPorId(@PathVariable MovementType type) {
        return ResponseEntity.ok(stockMovementService.obterTodosPorTipo(type));
    }

    //todo refatorar gets para somente mostrar o nome do produto.
}
