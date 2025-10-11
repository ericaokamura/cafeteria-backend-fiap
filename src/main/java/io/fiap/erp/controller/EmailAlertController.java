package io.fiap.erp.controller;

import io.fiap.erp.model.dto.ItemEstoqueDTO;
import io.fiap.erp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alert")
public class EmailAlertController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/emailBatch")
    public ResponseEntity<String> sendBatchEmailAlert(@RequestBody List<ItemEstoqueDTO> alerts) {
        if (alerts == null || alerts.isEmpty()) {
            return ResponseEntity.badRequest().body("Não há itens do estoque abaixo da quantidade ideal.");
        }

        emailService.sendBatchLowStockAlert(alerts);
        return ResponseEntity.ok("Batch email enviado.");
    }
}
