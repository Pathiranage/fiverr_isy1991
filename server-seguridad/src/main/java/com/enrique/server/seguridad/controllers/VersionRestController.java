package com.enrique.server.seguridad.controllers;

import com.enrique.server.seguridad.models.entity.Version;
import com.enrique.server.seguridad.services.IVersionService;
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
public class VersionRestController {

    @Autowired
    private IVersionService versionService;

    @GetMapping("/versiones")
    public List<Version> index() {
        return versionService.findAll();
    }

    @GetMapping("/versiones/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Version version = versionService.findById(id);

        Map<String, Object> response = new HashMap<>();

        if (version == null) {
            response.put("mensaje", "La version con ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Version>(version, HttpStatus.OK);
    }


    //	@Secured("ROLE_ADMIN")
    @PostMapping("/versiones")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Version version) {

        Version versionNew = null;
        Map<String, Object> response = new HashMap<>();

        try {

            versionNew = versionService.save(version);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La version ha sido creada");
        response.put("version", versionNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    //@Secured("ROLE_ADMIN")
    @PutMapping("/versiones/{id}")
    public ResponseEntity<?> update(@RequestBody Version version, @PathVariable Long id) {


        Version versionActual = versionService.findById(id);

        Version versionUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (versionActual == null) {
            response.put("mensaje", "Error: no se pudo editar ya que no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }


        try {
            versionActual.setNombre(version.getNombre());

            versionUpdated = versionService.save(versionActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Version actualizado con exito");
        response.put("versiones", versionUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/versiones/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        versionService.delete(id);

        response.put("mensaje", "Version eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @GetMapping("/versiones/product/{id}")
    public ResponseEntity<?> showByProductId(@PathVariable Long id) {

        List<Version> version = versionService.showByProductId(id);

        Map<String, Object> response = new HashMap<>();

        if (version == null) {
            response.put("mensaje", "La version con ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(version);
    }

}