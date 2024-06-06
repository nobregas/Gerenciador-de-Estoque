package br.com.estoqueapi.estoqueapi.services;

import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockEntryMovementDTO;
import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockLeaveMovementDTO;
import br.com.estoqueapi.estoqueapi.entities.Produto;
import br.com.estoqueapi.estoqueapi.entities.StockMovement;
import br.com.estoqueapi.estoqueapi.entities.enums.MovementType;
import br.com.estoqueapi.estoqueapi.exceptions.produtos.ProdutoNotFoundException;
import br.com.estoqueapi.estoqueapi.repositories.ProdutoRepository;
import br.com.estoqueapi.estoqueapi.repositories.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final ProdutoService produtoService;
    private final StockMovementRepository stockMovementRepository;

    public StockMovement registrarEntrada(StockEntryMovementDTO dto) {
        Produto produto = produtoService.findById(dto.produtoId());


        produto.addQuantidade(dto.quantidade());
        produto = produtoService.save(produto);

        StockMovement stockMovement = new StockMovement(dto, produto);

        return stockMovementRepository.save(stockMovement);
    }

    public StockMovement registrarSaida(StockLeaveMovementDTO dto) {
        Produto produto = produtoService.findById(dto.produtoId());

        produto.diminuirQuantidade(dto.quantidade());
        produto = produtoService.save(produto);

        StockMovement stockMovement = new StockMovement(dto, produto);

        return stockMovementRepository.save(stockMovement);
    }

    public List<StockMovement> obterTodasMovimentacoes() {
        return stockMovementRepository.findAll();
    }

    public List<StockMovement> obterTodasMovimentacoesDoProdutoId(String id) {
        Produto produto = produtoService.findById(id);

        return stockMovementRepository.findAllByProdutoIn(produto);
    }

    public List<StockMovement> obterTodosPorTipo(MovementType movementType){
        return null;
    }

}
