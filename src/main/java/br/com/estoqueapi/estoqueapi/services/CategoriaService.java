package br.com.estoqueapi.estoqueapi.services;

import br.com.estoqueapi.estoqueapi.dtos.categoria.CategoriaDTO;
import br.com.estoqueapi.estoqueapi.entities.Categoria;
import br.com.estoqueapi.estoqueapi.exceptions.categorias.CategoriaAlreadyExistsException;
import br.com.estoqueapi.estoqueapi.exceptions.categorias.CategoriaNotFoundException;
import br.com.estoqueapi.estoqueapi.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(String id) {
        return categoriaRepository.findById(id)
                .orElseThrow(CategoriaNotFoundException::new);
    }

    public Categoria findByNome(String nome) {
        return categoriaRepository.findByNome(nome)
                .orElseThrow(CategoriaNotFoundException::new);
    }

    public Categoria save(CategoriaDTO categoria) {


        if(categoriaRepository.existsByNome(categoria.nome())) {
            throw new CategoriaAlreadyExistsException();
        } else {
            Categoria categoriaEntity = new Categoria(categoria);
            categoriaEntity.setCreated_at(Instant.now());

            return categoriaRepository.save(categoriaEntity);
        }
    }

    public List<Categoria> obterCategoriasPorNome(List<String> nomes) {
        List<Categoria> categorias = new ArrayList<>();

        for(String nome : nomes) {
            Categoria categoria = findByNome(nome);
            categorias.add(categoria);
        }
        return categorias;
    }

    public Categoria update(String id, CategoriaDTO categoriaDTO) {
        Categoria categoriaEntity = findById(id);

        atualizarCampos(categoriaEntity, categoriaDTO);
        return categoriaRepository.save(categoriaEntity);
    }


    public void delete(String id) {
        Categoria categoriaEntity = findById(id);
        categoriaRepository.delete(categoriaEntity);
    }

    private void atualizarCampos(Categoria entity, CategoriaDTO dto) {
        if(!dto.nome().isBlank() && !entity.getNome().equals(dto.nome())) {
            entity.setNome(dto.nome());
        }
        if(!dto.descricao().isBlank() && !entity.getDescricao().equals(dto.descricao())) {
            entity.setDescricao(dto.descricao());
        }
        entity.setUpdated_at(Instant.now());
    }


}
