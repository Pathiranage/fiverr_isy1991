package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.dao.IProductoDao;
import com.enrique.server.seguridad.models.entity.Producto;
import com.enrique.server.seguridad.models.entity.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        // TODO Auto-generated method stub
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        // TODO Auto-generated method stub
        return productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productoDao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        // TODO Auto-generated method stub
        return productoDao.findById(id).orElse(null);
    }

    // Método extra para seleccionar la última version mas reciente
    @Override
    public Version findByIdVersion(long id) {

        return productoDao.findByIdVersion(id);
    }


}
