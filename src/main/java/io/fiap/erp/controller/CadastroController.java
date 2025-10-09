package io.fiap.erp.controller;

import io.fiap.erp.model.dto.UsuarioDTO;
import io.fiap.erp.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping("")
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(cadastroService.cadastrarUsuario(usuario));
    }

    @GetMapping("/nomeUsuario")
    public ResponseEntity<UsuarioDTO> retornarUsuario(@RequestParam("nomeUsuario") String nomeUsuario) {
        return ResponseEntity.ok(cadastroService.retornarUsuario(nomeUsuario));
    }

    @PutMapping("")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.ok(cadastroService.atualizarUsuario(usuario));
    }

    @DeleteMapping("/nomeUsuario")
    public ResponseEntity<Void> deletarUsuario(@RequestParam("nomeUsuario") String nomeUsuario) {
        cadastroService.deletarUsuario(nomeUsuario);
        return ResponseEntity.ok().build();
    }
}
