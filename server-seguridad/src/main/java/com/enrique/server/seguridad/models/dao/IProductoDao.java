package com.enrique.server.seguridad.models.dao;

import com.enrique.server.seguridad.models.entity.Producto;
import com.enrique.server.seguridad.models.entity.Version;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface IProductoDao extends CrudRepository<Producto, Long> {

	/*
	@Query("SELECT e FROM Version e JOIN e.usuarios usuario JOIN usuario.evaluaciones evaluacion where usuario.id = :id_producto")
	public List<Evaluacion> findAllById(@Param("id_producto") long id);*/
	
	/*
	 * 
	@Query("SELECT DISTINCT e FROM Evaluacion e JOIN e.usuarios usuario JOIN usuario.evaluaciones evaluacion where usuario.id = :id_user")
	public List<Evaluacion> findAllById(@Param("id_user") long id);
	
	
	SELECT MAX(versiones.nombre), producto_id FROM db_springboot_seg.versiones WHERE producto_id=1
	
	 */


    @Query("SELECT MAX(e.nombre) FROM Version e JOIN e.producto p JOIN p.versiones version where p.id = :id_prod")
    public Version findByIdVersion(@Param("id_prod") long id);

}

