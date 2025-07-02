package io.fiap.erp.service;

import io.fiap.erp.mapper.ProdutoMapper;
import io.fiap.erp.model.Produto;
import io.fiap.erp.model.dto.ProdutoDTO;
import io.fiap.erp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.convertDTOToModel(produtoDTO);
        produto.setDataHoraUltimaAtualizacao(LocalDateTime.now());
        Produto produtoSalvo = produtoRepository.save(produto);
        return ProdutoMapper.convertModelToDTO(produtoSalvo);
    }

    public ProdutoDTO atualizarProduto(Long idProduto, ProdutoDTO produtoDTO) {
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        if(optionalProduto.isEmpty()) {
            throw new RuntimeException(); // criar exception customizada
        }
        Produto produto = optionalProduto.get();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setValorUnitario(produtoDTO.getValorUnitario());
        produto.setDataHoraUltimaAtualizacao(LocalDateTime.now());
        Produto produtoAtualizado = produtoRepository.save(produto);
        return ProdutoMapper.convertModelToDTO(produtoAtualizado);
    }

    public ProdutoDTO retornarProduto(Long idProduto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        if(optionalProduto.isEmpty()) {
            throw new RuntimeException(); // criar exception customizada
        }
        Produto produto = optionalProduto.get();
        return ProdutoMapper.convertModelToDTO(produto);
    }
    public List<ProdutoDTO> retornarProdutos() {
        return ProdutoMapper.converModelListToDTOList(produtoRepository.findAll());
    }

    public void deletarProduto(Long idProduto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        if(optionalProduto.isEmpty()) {
            throw new RuntimeException(); // criar exception customizada
        }
        Produto produto = optionalProduto.get();
        produtoRepository.delete(produto);
    }
}
