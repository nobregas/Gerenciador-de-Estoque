package br.com.estoqueapi.estoqueapi.services;

import br.com.estoqueapi.estoqueapi.dtos.movimentacao.StockEntryMovementDTO;
import br.com.estoqueapi.estoqueapi.entities.Produto;
import br.com.estoqueapi.estoqueapi.entities.StockMovement;
import br.com.estoqueapi.estoqueapi.exceptions.produtos.ProdutoNotFoundException;
import br.com.estoqueapi.estoqueapi.repositories.ProdutoRepository;
import br.com.estoqueapi.estoqueapi.repositories.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final ProdutoRepository produtoRepository;
    private final StockMovementRepository stockMovementRepository;

    public StockMovement registrarEntrada(StockEntryMovementDTO dto) {
        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(ProdutoNotFoundException::new);

        produto.addQuantidade(dto.quantidade());
        produto = produtoRepository.save(produto);

        StockMovement stockMovement = new StockMovement(dto, produto);

        return stockMovementRepository.save(stockMovement);
    }

    public void registrarSaida() {

    }

    public List<StockMovement> obterTodasMovimentacoes() {
        return null;
    }

    public List<StockMovement> obterTodasMovimentacoesDoProduto() {
        return null;
    }

}
