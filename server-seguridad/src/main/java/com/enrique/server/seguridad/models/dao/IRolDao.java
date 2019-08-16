package com.enrique.server.seguridad.models.dao;


import com.enrique.server.seguridad.models.entity.Rol;
import org.springframework.data.repository.CrudRepository;

public interface IRolDao extends CrudRepository<Rol, Long> {

}

