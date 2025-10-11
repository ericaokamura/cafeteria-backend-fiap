package io.fiap.erp.controller;

import io.fiap.erp.model.dto.ItemEstoqueDTO;
import io.fiap.erp.service.ItemEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("itensEstoque")
public class ItemEstoqueController {

    @Autowired
    private ItemEstoqueService itemEstoqueService;

    @PutMapping("/id/{id}")
    public ResponseEntity<ItemEstoqueDTO> atualizarItemEstoque(@PathVariable("id") Long id, @RequestBody ItemEstoqueDTO itemEstoqueDTO) {
        return ResponseEntity.ok(this.itemEstoqueService.atualizarItemEstoque(id, itemEstoqueDTO));
    }

    @GetMapping("/")
    public ResponseEntity<List<ItemEstoqueDTO>> retornarItensEstoque() {
        return ResponseEntity.ok(this.itemEstoqueService.retornarItensEstoque());
    }

}
