package com.enrique.server.seguridad.controllers;


import com.enrique.server.seguridad.models.entity.Empresa;
import com.enrique.server.seguridad.services.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"http://localhost:4200"})

@RestController

@RequestMapping("/api")

public class EmpresaRestController {


    @Autowired

    private IEmpresaService empresaService;


    @GetMapping("/empresas")

    public List<Empresa> index() {

        return empresaService.findAll();

    }


    @GetMapping("/empresas/{id}")

    public ResponseEntity<?> show(@PathVariable Long id) {


        Empresa empresa = empresaService.findById(id);


        Map<String, Object> response = new HashMap<>();


        if (empresa == null) {

            response.put("mensaje", "La empresa con ID: ".concat(id.toString().concat(" no existe")));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

        }


        return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);

    }


    // Devuelve el cliente creado, recibirá el objeto cliente en JSON

    //	@Secured("ROLE_ADMIN")

    @PostMapping("/empresas")

    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<?> create(@RequestBody Empresa empresa) {


        Empresa empresaNew = null;

        Map<String, Object> response = new HashMap<>();


        try {


            empresaNew = empresaService.save(empresa);

        } catch (DataAccessException e) {

            response.put("mensaje", "Error al realizar el insert");

            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }


        response.put("mensaje", "La empresa ha sido creada");

        response.put("empresa", empresaNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }


    // Empleado para actualizar, no como el POST

    //@Secured("ROLE_ADMIN")

    @PutMapping("/empresas/{id}")

    public ResponseEntity<?> update(@RequestBody Empresa empresa, @PathVariable Long id) {


        Empresa empresaActual = empresaService.findById(id);


        Empresa empresaUpdated = null;


        Map<String, Object> response = new HashMap<>();


        if (empresaActual == null) {

            response.put("mensaje", "Error: no se pudo editar ya que no existe");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

        }


        try {

            empresaActual.setNombre(empresa.getNombre());

            empresaActual.setDireccion(empresa.getDireccion());

            empresaActual.setTelefono(empresa.getTelefono());

            empresaActual.setCif(empresa.getCif());


            empresaActual.setNombre_empleado(empresa.getNombre_empleado());

            empresaActual.setApellido_empleado(empresa.getApellido_empleado());

            empresaActual.setCargo_empleado(empresa.getCargo_empleado());

            empresaActual.setEmail_empleado(empresa.getEmail_empleado());


            empresaUpdated = empresaService.save(empresaActual);


        } catch (DataAccessException e) {

            response.put("mensaje", "Error al actualizar");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }


        response.put("mensaje", "Empresa actualizado con exito");

        response.put("empresas", empresaUpdated);


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }


    // Eliminamos el cliente

    //@Secured("ROLE_ADMIN")

    @DeleteMapping("/empresas/{id}")

    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        empresaService.delete(id);


        response.put("mensaje", "Empresa eliminado con éxito");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);


    }


}