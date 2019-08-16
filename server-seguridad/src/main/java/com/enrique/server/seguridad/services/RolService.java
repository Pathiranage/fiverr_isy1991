package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.dao.IRolDao;
import com.enrique.server.seguridad.models.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolService implements IRolService {

    @Autowired
    private IRolDao rolDao;


    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {

        return (List<Rol>) rolDao.findAll();
    }

    @Override
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

    @Override
    public void delete(Long id) {
        rolDao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Rol findById(Long id) {

        return rolDao.findById(id).orElse(null);
    }


}
