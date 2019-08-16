package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.dao.IEmpleadoDao;
import com.enrique.server.seguridad.models.dao.IEmpresaDao;
import com.enrique.server.seguridad.models.entity.Empleado;
import com.enrique.server.seguridad.models.entity.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService implements IEmpresaService {

    @Autowired
    private IEmpresaDao empresaDao;

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Empresa> findAll() {

        return (List<Empresa>) empresaDao.findAll();
    }

    @Override
    @Transactional
    public Empresa save(Empresa empresa) {

        return empresaDao.save(empresa);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        empresaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Empresa findById(Long id) {

        return empresaDao.findById(id).orElse(null);
    }


    // Para encontrar el empleado correspondiente a esa empresa
    @Override
    public Empleado findEmpleadoById(Long id) {
        return this.empleadoDao.findById(id).orElse(null);
    }


}
