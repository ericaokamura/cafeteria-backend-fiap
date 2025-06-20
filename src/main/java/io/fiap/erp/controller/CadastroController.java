package io.fiap.erp.controller;

import io.fiap.erp.model.dto.UsuarioDTO;
import io.fiap.erp.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping("")
    public ResponseEntity<UsuarioDTO> cadastrarAluno(@RequestBody UsuarioDTO aluno) {
        return ResponseEntity.ok(cadastroService.cadastrarUsuario(aluno));
    }
}
