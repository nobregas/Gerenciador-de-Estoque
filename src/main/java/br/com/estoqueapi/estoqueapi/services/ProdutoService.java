package br.com.estoqueapi.estoqueapi.services;

import br.com.estoqueapi.estoqueapi.dtos.categoria.CategoriasListDTO;
import br.com.estoqueapi.estoqueapi.dtos.produto.ProdutoDTO;
import br.com.estoqueapi.estoqueapi.entities.Categoria;
import br.com.estoqueapi.estoqueapi.entities.Produto;
import br.com.estoqueapi.estoqueapi.exceptions.produtos.ProdutoNotFoundException;
import br.com.estoqueapi.estoqueapi.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(String id) {
        return produtoRepository.findById(id)
                .orElseThrow(ProdutoNotFoundException::new);
    }

    public List<Produto> findAllByCategoria(CategoriasListDTO dto){
        List<Categoria> categorias = categoriaService.obterCategoriasPorNome(dto.categoriasNome());
        return produtoRepository.findAllByCategoriasIn(categorias);
    }

    public Produto save(ProdutoDTO produto) {
        List<Categoria> categorias = categoriaService.obterCategoriasPorNome(produto.categoriaNomes());

        Produto produtoEntity = new Produto(produto, categorias);

        return produtoRepository.save(produtoEntity);
    }

    public Produto update(String id, ProdutoDTO produtoDTO) {
        Produto produtoEntity = findById(id);

        atualizarCampos(produtoEntity, produtoDTO);
        return produtoRepository.save(produtoEntity);
    }

    public void delete(String id) {
        Produto produto = findById(id);
        produtoRepository.delete(produto);
    }

    private void atualizarCampos(Produto entity, ProdutoDTO dto) {
        if(!dto.nome().isBlank() && !entity.getNome().equals(dto.nome())) {
            entity.setNome(dto.nome());
        }
        if(!dto.descricao().isBlank() && !entity.getDescricao().equals(dto.descricao())) {
            entity.setDescricao(dto.descricao());
        }

        final BigDecimal ZERO = new BigDecimal(0);

        if(dto.preco() != null
                && !dto.preco().equals(ZERO)
                && !entity.getPreco().equals(dto.preco())
        ) {
            entity.setPreco(dto.preco());
        }

        if(dto.quantidade() != null
                && !dto.quantidade().equals(0)
                && !entity.getQuantidade().equals(dto.quantidade())
        ) {
            entity.setQuantidade(dto.quantidade());
        }
        atualizarCategoria(entity, dto);

        entity.setUpdated_at(Instant.now());
    }

    private void atualizarCategoria(Produto entity, ProdutoDTO dto) {
        if(dto.categoriaNomes() != null) {
            List<Categoria> novasCategorias = categoriaService
                    .obterCategoriasPorNome(dto.categoriaNomes());

            entity.setCategorias(novasCategorias);
        }
    }
}
