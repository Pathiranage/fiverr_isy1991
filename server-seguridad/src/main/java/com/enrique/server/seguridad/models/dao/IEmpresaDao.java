package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface IEmpresaDao extends CrudRepository<Empresa, Long> {

}


