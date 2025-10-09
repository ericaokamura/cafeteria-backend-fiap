package io.fiap.erp.service;

import io.fiap.erp.exception.UsuarioJaExistenteException;
import io.fiap.erp.exception.UsuarioNaoExisteException;
import io.fiap.erp.mapper.UsuarioMapper;
import io.fiap.erp.model.TipoFuncionario;
import io.fiap.erp.model.Usuario;
import io.fiap.erp.model.dto.UsuarioDTO;
import io.fiap.erp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public UsuarioDTO retornarUsuario(String nomeUsuario) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(optionalUsuario.isEmpty()) {
            throw new UsuarioNaoExisteException("Usuário não existe: " + nomeUsuario);
        }
        return UsuarioMapper.convertModelToDTO(optionalUsuario.get());
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByNomeUsuario(usuarioDTO.getNomeUsuario());
        if(optionalUsuario.isEmpty()) {
            throw new UsuarioJaExistenteException("Usuário não existe: " + usuarioDTO.getNomeUsuario());
        }
        Usuario usuario = optionalUsuario.get();
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setTipoFuncionario(TipoFuncionario.valueOf(usuarioDTO.getTipoFuncionario()));
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario.setDataHoraCadastro(LocalDateTime.now());
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
        return UsuarioMapper.convertModelToDTO(usuarioSalvo);
    }

    public void deletarUsuario(String nomeUsuario) {
        Optional<Usuario> optionalUsuario = this.usuarioRepository.findByNomeUsuario(nomeUsuario);
        if(optionalUsuario.isEmpty()) {
            throw new UsuarioJaExistenteException("Usuário não existe: " + nomeUsuario);
        }
        this.usuarioRepository.delete(optionalUsuario.get());
    }
}