package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.entity.Empleado;
import com.enrique.server.seguridad.models.entity.Empresa;

import java.util.List;


public interface IEmpresaService {

    public List<Empresa> findAll();

    public Empresa save(Empresa empresa);

    public void delete(Long id);

    public Empresa findById(Long id);
	
    public Empleado findEmpleadoById(Long id);

}


