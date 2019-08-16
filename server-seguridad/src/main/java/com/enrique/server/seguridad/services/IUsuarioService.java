package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.entity.Rol;
import com.enrique.server.seguridad.models.entity.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUsuarioService {

    Usuario findByUsername(String username);

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    @Transactional
    List<Usuario> saveAll(List<Usuario> usuario);

    void delete(Long id);

    Usuario findById(Long id);

    Rol findRolById(Long id);

    List<Usuario> findUsersByRole(String role);
}
