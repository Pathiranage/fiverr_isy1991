package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Version;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IVersionDao extends CrudRepository<Version, Long> {
 List<Version> findByProductoId(@Param("id") Long id);
}




