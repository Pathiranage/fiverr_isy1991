package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.entity.Charateristics;
import com.enrique.server.seguridad.models.entity.Evaluacion;
import com.enrique.server.seguridad.models.entity.Macrocharacteristic;

import java.util.List;

public interface IEvaluacionService {
    List<Evaluacion> findAll();

    Evaluacion save(Evaluacion evaluacion);

    void delete(Long id);

    Evaluacion findById(Long id);

    List<Evaluacion> findAllById(long id);

    Macrocharacteristic findMacrocharacteristicById(Long id);

    List<Charateristics> charasteristicsByEvaluacionesId(Long id);

    List<Evaluacion> findAllByUserId(long id);

}


