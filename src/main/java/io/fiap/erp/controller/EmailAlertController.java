package io.fiap.erp.controller;

import io.fiap.erp.model.dto.ItemEstoqueDTO;
import io.fiap.erp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alert")
public class EmailAlertController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/emailBatch/{nomeUsuario}")
    public ResponseEntity<String> sendBatchEmailAlert(@RequestBody List<ItemEstoqueDTO> alerts, @PathVariable("nomeUsuario") String nomeUsuario) {
        if (alerts == null || alerts.isEmpty()) {
            return ResponseEntity.badRequest().body("Não há itens do estoque abaixo da quantidade ideal.");
        }

        emailService.sendBatchLowStockAlert(alerts, nomeUsuario);
        return ResponseEntity.ok("Batch email enviado.");
    }
}
