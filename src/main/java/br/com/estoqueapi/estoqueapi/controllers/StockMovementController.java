package br.com.estoqueapi.estoqueapi.controllers;

import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockEntryMovementDTO;
import br.com.estoqueapi.estoqueapi.entities.StockMovement;
import br.com.estoqueapi.estoqueapi.services.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @PostMapping("/entrada")
    public ResponseEntity<StockMovement> registrarEntrada(
            @Valid @RequestBody StockEntryMovementDTO dto) {

        return ResponseEntity.ok(stockMovementService.registrarEntrada(dto));
    }


}
