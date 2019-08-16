package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.dao.IVersionDao;
import com.enrique.server.seguridad.models.entity.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VersionService implements IVersionService {

    @Autowired
    private IVersionDao versionDao;


    @Override
    @Transactional(readOnly = true)
    public List<Version> findAll() {

        return (List<Version>) versionDao.findAll();
    }


    @Override
    @Transactional
    public Version save(Version version) {

        return versionDao.save(version);
    }


    @Override
    @Transactional
    public void delete(Long id) {

        versionDao.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Version findById(Long id) {

        return versionDao.findById(id).orElse(null);
    }

    @Override
    public List<Version> showByProductId(Long id) {
        return versionDao.findByProductoId(id);
    }


    @Override
    public Version findByVersion(String version) {
        // TODO Auto-generated method stub
        return null;
    }


}



