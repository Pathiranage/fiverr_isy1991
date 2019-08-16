package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Charateristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICharateristicsDao extends JpaRepository<Charateristics, Long> {
    List<Charateristics> findAllByMacrocharacteristicId(@Param("id") long id);
}
