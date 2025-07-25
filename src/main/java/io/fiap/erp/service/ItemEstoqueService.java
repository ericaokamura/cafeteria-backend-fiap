package io.fiap.erp.service;

import io.fiap.erp.mapper.ItemEstoqueMapper;
import io.fiap.erp.model.ItemEstoque;
import io.fiap.erp.model.dto.ItemEstoqueDTO;
import io.fiap.erp.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemEstoqueService {

    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;

    public ItemEstoqueDTO salvarItemEstoque(ItemEstoqueDTO itemEstoqueDTO) {
        ItemEstoque itemEstoque = ItemEstoqueMapper.convertDTOToModel(itemEstoqueDTO);
        itemEstoque.setDataHoraUltimaAtualizacao(LocalDateTime.now());
        ItemEstoque itemEstoqueSalvo = itemEstoqueRepository.save(itemEstoque);
        return ItemEstoqueMapper.convertModelToDTO(itemEstoqueSalvo);
    }

    public List<ItemEstoqueDTO> retornarItensEstoque() {
        return ItemEstoqueMapper.convertModelListToDTOList(itemEstoqueRepository.findAll());
    }
}
