package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Macrocharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMacrocharacteristicDao extends JpaRepository<Macrocharacteristic, Long> {
}
