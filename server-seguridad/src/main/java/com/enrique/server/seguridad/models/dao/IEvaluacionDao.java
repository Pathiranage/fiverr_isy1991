package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEvaluacionDao extends JpaRepository<Evaluacion, Long> {

	@Query("SELECT DISTINCT e FROM Evaluacion e JOIN e.usuarios usuario JOIN usuario.evaluaciones evaluacion where usuario.id = :id_user")
	public List<Evaluacion> findAllById(@Param("id_user") long id);


	List<Evaluacion> findAllByUsuarios_Id(Long id);

}
