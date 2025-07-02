package io.fiap.erp.mapper;

import io.fiap.erp.model.Produto;
import io.fiap.erp.model.dto.ProdutoDTO;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {

    public static Produto convertDTOToModel(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setNome(dto.getNome());
        produto.setValorUnitario(dto.getValorUnitario());
        produto.setTags(dto.getTags());
        produto.setDataHoraUltimaAtualizacao(dto.getDataHoraUltimaAtualizacao());
        return produto;
    }

    public static ProdutoDTO convertModelToDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setValorUnitario(produto.getValorUnitario());
        dto.setTags(produto.getTags());
        dto.setDataHoraUltimaAtualizacao(produto.getDataHoraUltimaAtualizacao());
        return dto;
    }

    public static List<Produto> converDTOListToModelList(List<ProdutoDTO> dtos) {
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoDTO dto : dtos) {
            Produto produto = new Produto();
            produto.setNome(dto.getNome());
            produto.setDescricao(dto.getDescricao());
            produto.setValorUnitario(dto.getValorUnitario());
            produto.setTags(dto.getTags());
            produto.setDataHoraUltimaAtualizacao(dto.getDataHoraUltimaAtualizacao());
            produtos.add(produto);
        }
        return produtos;
    }

    public static List<ProdutoDTO> converModelListToDTOList(List<Produto> produtos) {
        List<ProdutoDTO> dtos = new ArrayList<>();
        for (Produto produto : produtos) {
            ProdutoDTO dto = new ProdutoDTO();
            dto.setId(produto.getId());
            dto.setNome(produto.getNome());
            dto.setDescricao(produto.getDescricao());
            dto.setValorUnitario(produto.getValorUnitario());
            dto.setTags(produto.getTags());
            dto.setDataHoraUltimaAtualizacao(produto.getDataHoraUltimaAtualizacao());
            dtos.add(dto);
        }
        return dtos;
    }
}
