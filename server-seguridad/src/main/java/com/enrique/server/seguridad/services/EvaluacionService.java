package com.enrique.server.seguridad.services;

import com.enrique.server.seguridad.models.dao.ICharateristicsDao;
import com.enrique.server.seguridad.models.dao.IEvaluacionDao;
import com.enrique.server.seguridad.models.dao.IMacrocharacteristicDao;
import com.enrique.server.seguridad.models.entity.Charateristics;
import com.enrique.server.seguridad.models.entity.Evaluacion;
import com.enrique.server.seguridad.models.entity.Macrocharacteristic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EvaluacionService implements IEvaluacionService {

    @Autowired
    private IEvaluacionDao evaluacionDao;
    @Autowired
    private ICharateristicsDao charateristicsDao;
    @Autowired
    private IMacrocharacteristicDao macrocharacteristicDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evaluacion> findAll() {

        return (List<Evaluacion>) evaluacionDao.findAll();
    }

    @Override
    @Transactional
    public Evaluacion save(Evaluacion evaluacion) {
        Evaluacion save = evaluacionDao.save(evaluacion);
        List<Charateristics> charateristics = save.getMacrocharacteristic().getCharateristics();
        charateristics.forEach(charateristics1 -> {
            charateristics1.setMacrocharacteristic(save.getMacrocharacteristic());
            charateristics1.getProperties().forEach(properties -> properties.setCharateristics(charateristics1));
        });
        charateristicsDao.saveAll(charateristics);
        return findById(save.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {

        evaluacionDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Evaluacion findById(Long id) {

        return evaluacionDao.findById(id).orElse(null);
    }

    @Override
    public List<Evaluacion> findAllById(long id) {
        return evaluacionDao.findAllById(id);
    }

    @Override
    public Macrocharacteristic findMacrocharacteristicById(Long id) {
        return this.macrocharacteristicDao.findById(id).orElse(null);
    }

    @Override
    public List<Charateristics> charasteristicsByEvaluacionesId(Long id) {
        Evaluacion byId = findById(id);
        if (byId == null) {
            return null;
        }
        List<Charateristics> allByMacrocharacteristicId = charateristicsDao.findAllByMacrocharacteristicId(byId.getMacrocharacteristic().getId());
        return allByMacrocharacteristicId;
    }

    @Override
    public List<Evaluacion> findAllByUserId(long id) {

        return evaluacionDao.findAllByUsuarios_Id(id);
    }

	/*
	@Override
	public List<Evaluacion> findAllById(long id) {
		return evaluacionDao.findAllById(id);
	}
*/

}
