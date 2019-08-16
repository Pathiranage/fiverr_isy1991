package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.entity.Producto;
import com.enrique.server.seguridad.models.entity.Version;

import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();

    public Producto save(Producto producto);

    public void delete(Long id);

    public Producto findById(Long id);

    public Version findByIdVersion(long id);

}
