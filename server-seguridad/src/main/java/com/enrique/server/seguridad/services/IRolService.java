package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.entity.Rol;

import java.util.List;


public interface IRolService {

    public List<Rol> findAll();

    public Rol save(Rol rol);

    public void delete(Long id);

    public Rol findById(Long id);
}
