package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.entity.Version;

import java.util.List;

public interface IVersionService {

    Version findByVersion(String version);

    List<Version> findAll();

    Version save(Version usuario);

    void delete(Long id);

    Version findById(Long id);

    List<Version> showByProductId(Long id);
}
