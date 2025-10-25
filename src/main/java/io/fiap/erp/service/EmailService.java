package io.fiap.erp.service;

import io.fiap.erp.model.dto.ItemEstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendBatchLowStockAlert(List<ItemEstoqueDTO> alerts, String nomeUsuario) {
        StringBuilder body = new StringBuilder();
        body.append("Os seguintes produtos estão com estoque baixo:\n\n");

        for (ItemEstoqueDTO alert : alerts) {
            body.append(String.format(
                    "- %s: atual %d / ideal %d%n",
                    alert.getDescricao(), alert.getQuantidadeAtual(), alert.getQuantidadeIdeal()
            ));
        }

        body.append("\nPor favor, reabasteça o estoque conforme necessário.");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(nomeUsuario);
        message.setSubject("⚠️ Alerta de Estoque Baixo - " + alerts.size() + " produto(s)");
        message.setText(body.toString());

        mailSender.send(message);
    }
}

