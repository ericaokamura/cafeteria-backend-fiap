package io.fiap.erp.controller;

import io.fiap.erp.model.dto.ItemPedidoDTO;
import io.fiap.erp.model.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.fiap.erp.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<PedidoDTO> salvarPedido(@RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.ok(this.pedidoService.salvarProduto(pedidoDTO));
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable("idPedido") Long idPedido, @RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.ok(this.pedidoService.atualizarPedido(idPedido, pedidoDTO));
    }

    @PatchMapping("/{idPedido}/alterarFormaPagamento")
    public ResponseEntity<PedidoDTO> alterarFormaPagamento(@PathVariable("idPedido") Long idPedido, @RequestParam("formaPagamento") String formaPagamento) {
        return ResponseEntity.ok(this.pedidoService.alterarFormaPagamento(idPedido, formaPagamento));
    }

    @PatchMapping("/{idPedido}/cancelar")
    public ResponseEntity<PedidoDTO> cancelarPedido(@PathVariable("idPedido") Long idPedido) {
        return ResponseEntity.ok(this.pedidoService.cancelarPedido(idPedido));
    }

    @PatchMapping("/{idPedido}/inserirItem/{idProduto}")
    public ResponseEntity<PedidoDTO> inserirItemPedido(@PathVariable("idPedido") Long idPedido, @PathVariable("idProduto") Long idProduto, @RequestParam("quantidade") Long quantidade) {
        this.pedidoService.inserirItemPedido(idPedido, idProduto, quantidade);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{idPedido}/finalizar")
    public ResponseEntity<PedidoDTO> finalizarPedido(@PathVariable("idPedido") Long idPedido, @RequestParam("formaPagamento") String formaPagamento) {
        return ResponseEntity.ok(this.pedidoService.finalizarPedido(idPedido, formaPagamento));
    }

    @PatchMapping("/{idPedido}/enviar")
    public ResponseEntity<PedidoDTO> enviarPedido(@PathVariable("idPedido") Long idPedido) {
        return ResponseEntity.ok(this.pedidoService.enviarPedido(idPedido));
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoDTO> retornarPedido(@PathVariable("idPedido") Long idPedido) {
        return ResponseEntity.ok(this.pedidoService.retornarPedido(idPedido));
    }

    @GetMapping()
    public ResponseEntity<List<PedidoDTO>> retornarPedidos() {
        return ResponseEntity.ok(this.pedidoService.retornarPedidos());
    }

    @GetMapping("/{idPedido}/itens")
    public ResponseEntity<List<ItemPedidoDTO>> retornarItensPedido(@PathVariable("idPedido") Long idPedido) {
        return ResponseEntity.ok(this.pedidoService.retornarItensPedido(idPedido));
    }

    @DeleteMapping
    public ResponseEntity deletarTodosPedidos() {
        this.pedidoService.deletarTodosPedidos();
        return ResponseEntity.ok().build();
    }
}
