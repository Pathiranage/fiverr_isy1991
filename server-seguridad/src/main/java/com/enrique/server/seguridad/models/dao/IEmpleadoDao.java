package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoDao extends JpaRepository<Empleado, Long> {

}