package io.fiap.erp.controller;

import io.fiap.erp.service.ReportExporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("export")
public class ReportExportController {

    @Autowired
    private ReportExporterService reportExporterService;

    @GetMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> exportarRelatorioVendas(@RequestParam("dataInicio") String dataInicio, @RequestParam("dataFim") String dataFim) throws IOException {

        byte[] excelFile = reportExporterService.exportarRelatorioVendas(dataInicio, dataFim);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.xlsx");

        return new ResponseEntity<>(excelFile, headers, HttpStatus.OK);
    }

}
