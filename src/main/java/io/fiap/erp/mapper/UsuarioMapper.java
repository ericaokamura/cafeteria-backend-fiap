package io.fiap.erp.mapper;

import io.fiap.erp.model.Produto;
import io.fiap.erp.model.TipoFuncionario;
import io.fiap.erp.model.Usuario;
import io.fiap.erp.model.dto.ProdutoDTO;
import io.fiap.erp.model.dto.UsuarioDTO;

public class UsuarioMapper {

    public static Usuario convertDTOToModel(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(dto.getSenha());
        usuario.setTipoFuncionario(TipoFuncionario.valueOf(dto.getTipoFuncionario()));
        return usuario;
    }

    public static UsuarioDTO convertModelToDTO(Usuario model) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNomeUsuario(model.getNomeUsuario());
        dto.setSenha(model.getSenha());
        dto.setTipoFuncionario(model.getTipoFuncionario().name());
        return dto;
    }
}
