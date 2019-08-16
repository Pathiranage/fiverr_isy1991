package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Rol;
import com.enrique.server.seguridad.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
//Esta interfaz es usada para heredar todos los metodos de la clase CrudRepository

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    @Query("select e from Usuario e where e.username=?1")
    public Usuario findByUsername2(String username);

    public Rol findRolById(Long id);
    //public List<Evaluacion> selectAllByIdUser(@Param("id_user")long id);

    List<Usuario> findAllByRoles_Nombre(String role);
}