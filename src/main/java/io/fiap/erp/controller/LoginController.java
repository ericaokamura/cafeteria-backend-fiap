package io.fiap.erp.controller;

import io.fiap.erp.model.DadosAutenticacao;
import io.fiap.erp.model.DadosTokenJWT;
import io.fiap.erp.model.Usuario;
import io.fiap.erp.repository.UsuarioRepository;
import io.fiap.erp.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping()
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNomeUsuario(dados.nomeUsuario());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if(BCrypt.checkpw(dados.senha(), usuario.getSenha())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.nomeUsuario(), dados.senha(), usuario.getAuthorities());
                Authentication authentication = manager.authenticate(authenticationToken);
                String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
                return ResponseEntity.ok(new DadosTokenJWT(tokenJWT, usuario.getTipoFuncionario().name()));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
