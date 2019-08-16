package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.dao.IUsuarioDao;
import com.enrique.server.seguridad.models.entity.Rol;
import com.enrique.server.seguridad.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {

            logger.error("Error al loguearse, el usuario '" + username + "' no existe");
            throw new UsernameNotFoundException("Error al loguearse, el usuario '" + username + "' no existe");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        // TODO Auto-generated method stub
        return usuarioDao.findByUsername(username);
    }

    // Clase usada para la implementación de los metodos CRUD, a través del DAO cliente.

    // Solo sirve para mostrar datos, por lo tanto el readOnly
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {

        return (List<Usuario>) usuarioDao.findAll();
    }

    // Muetra mas aparte de los datos, por eso el Transactional a solas
    @Override
    @Transactional
    public Usuario save(Usuario usuario) {

        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public List<Usuario> saveAll(List<Usuario> usuario) {

        return (List<Usuario>) usuarioDao.saveAll(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        usuarioDao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {

        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findRolById(Long id) {

        return usuarioDao.findRolById(id);
    }

    @Override
    public List<Usuario> findUsersByRole(String role) {
        return usuarioDao.findAllByRoles_Nombre(role);
    }

}