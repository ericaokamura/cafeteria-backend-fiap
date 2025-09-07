package io.fiap.erp.service;

import io.fiap.erp.exception.UsuarioJaExistenteException;
import io.fiap.erp.exception.UsuarioNaoExisteException;
import io.fiap.erp.mapper.UsuarioMapper;
import io.fiap.erp.model.Usuario;
import io.fiap.erp.model.dto.UsuarioDTO;
import io.fiap.erp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByNomeUsuario(usuarioDTO.getNomeUsuario());
        if(optionalUsuario.isPresent()) {
            throw new UsuarioJaExistenteException("Usuário já existe: " + usuarioDTO.getNomeUsuario());
        }
        Usuario usuario = UsuarioMapper.convertDTOToModel(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
        return UsuarioMapper.convertModelToDTO(usuarioSalvo);
    }
}
