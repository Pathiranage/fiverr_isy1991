package com.enrique.server.seguridad.controllers;

import com.enrique.server.seguridad.models.entity.Rol;
import com.enrique.server.seguridad.services.IRolService;
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
public class RolRestController {

    @Autowired
    private IRolService rolService;

    @GetMapping("/roles")
    public List<Rol> index() {
        return rolService.findAll();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Rol rol = rolService.findById(id);

        Map<String, Object> response = new HashMap<>();

        if (rol == null) {
            response.put("mensaje", "El rol con ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Rol>(rol, HttpStatus.OK);
    }


    // Devuelve el producto creado, recibirá el objeto producto en JSON
    //	@Secured("ROLE_ADMIN")
    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Rol rol) {

        Rol rolNew = null;
        Map<String, Object> response = new HashMap<>();

        try {

            rolNew = rolService.save(rol);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El rol ha sido creado");
        response.put("rol", rolNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    // Eliminamos el producto
    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        rolService.delete(id);

        response.put("mensaje", "Rol eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    // Creado para mostrar la ultima version


}
