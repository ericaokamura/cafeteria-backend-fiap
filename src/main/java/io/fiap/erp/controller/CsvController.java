package io.fiap.erp.controller;

import io.fiap.erp.model.dto.ItemEstoqueDTO;
import io.fiap.erp.model.dto.ProdutoDTO;
import io.fiap.erp.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping(value = "/uploadProdutos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<ProdutoDTO> uploadCSVProducts(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        try {
            List<ProdutoDTO> products = csvService.csvToProducts(file.getInputStream());
            return products;
        } catch (Exception e) {
            throw new RuntimeException("Error processing file: " + e.getMessage());
        }
    }

    @PostMapping(value = "/uploadItensEstoque", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<ItemEstoqueDTO> uploadCSVItensEstoque(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        try {
            List<ItemEstoqueDTO> itensEstoque = csvService.csvToItensEstoque(file.getInputStream());
            return itensEstoque;
        } catch (Exception e) {
            throw new RuntimeException("Error processing file: " + e.getMessage());
        }
    }

}
