package io.fiap.erp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  nomeUsuario;
    private String senha;
    private String cpf;
    @Enumerated(value = EnumType.STRING)
    private TipoFuncionario tipoFuncionario;
    private LocalDateTime dataHoraCadastro;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
        String[] authorities = this.tipoFuncionario.getAuthorities().split(",");
        Arrays.asList(authorities).forEach(a -> authoritiesList.add(new SimpleGrantedAuthority(a)));
        return authoritiesList;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nomeUsuario;
    }
}
