package io.fiap.erp.controller;

import io.fiap.erp.model.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.fiap.erp.service.ProdutoService;

import java.util.List;


@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping()
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(this.produtoService.salvarProduto(produtoDTO));
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable("idProduto") Long idProduto, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(this.produtoService.atualizarProduto(idProduto, produtoDTO));
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity deletarProduto(@PathVariable("idProduto") Long idProduto) {
        this.produtoService.deletarProduto(idProduto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> retornarProduto(@PathVariable("idProduto") Long idProduto) {
        return ResponseEntity.ok(this.produtoService.retornarProduto(idProduto));
    }

    @GetMapping()
    public ResponseEntity<List<ProdutoDTO>> retornarProdutos() {
        return ResponseEntity.ok(this.produtoService.retornarProdutos());
    }

}
