package br.com.estoqueapi.estoqueapi.entities;

import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockEntryMovementDTO;
import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockLeaveMovementDTO;
import br.com.estoqueapi.estoqueapi.entities.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "stock_movements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {

    @Id
    private String id;

    @DBRef
    private Produto produto;

    private int quantidade;

    private MovementType movementType;

    private BigDecimal preco;

    private Instant created_at;


    public StockMovement(StockEntryMovementDTO dto, Produto produto) {
        this.produto = produto;
        this.quantidade = dto.quantidade();
        this.movementType = MovementType.IN;
        this.preco = dto.preco();
        this.created_at = Instant.now();
    }

    public StockMovement(StockLeaveMovementDTO dto, Produto produto) {
        this.produto = produto;
        this.quantidade = dto.quantidade();
        this.movementType = MovementType.OUT;
        this.preco = dto.preco();
        this.created_at = Instant.now();
    }
}
