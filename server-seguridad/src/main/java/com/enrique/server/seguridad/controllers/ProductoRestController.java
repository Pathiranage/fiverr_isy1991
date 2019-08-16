package com.enrique.server.seguridad.controllers;

import com.enrique.server.seguridad.models.entity.Producto;
import com.enrique.server.seguridad.models.entity.Version;
import com.enrique.server.seguridad.services.IProductoService;
import com.enrique.server.seguridad.services.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductoRestController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IVersionService versionService;


    @GetMapping("/productos")
    public List<Producto> index() {
        return productoService.findAll();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Producto producto = productoService.findById(id);

        Map<String, Object> response = new HashMap<>();

        if (producto == null) {
            response.put("mensaje", "El producto con ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }


    // Devuelve el producto creado, recibirá el objeto producto en JSON
    //	@Secured("ROLE_ADMIN")
    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Producto producto) {

        Producto productoNew = null;
        Map<String, Object> response = new HashMap<>();

        try {

            productoNew = productoService.save(producto);
            List<Version> versiones = new ArrayList<>(productoNew.getVersiones());
            for (Version version : versiones) {
                version.setProducto(productoNew);
                versionService.save(version);
            }

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El producto ha sido creado");
        response.put("producto", productoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    // Empleado para actualizar, no como el POST
    //@Secured("ROLE_ADMIN")
    @PutMapping("/productos/{id}")
    public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id) {


        Producto productoActual = productoService.findById(id);

        Producto productoUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (productoActual == null) {
            response.put("mensaje", "Error: no se pudo editar ya que no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            productoActual.setNombre(producto.getNombre());
            productoActual.setId(producto.getId());
            productoActual.setVersion(producto.getVersion());
            productoActual.setEmpresa(producto.getEmpresa());


            productoUpdated = productoService.save(productoActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Producto actualizado con éxito");
        response.put("empresas", productoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    // Eliminamos el producto
    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        productoService.delete(id);

        response.put("mensaje", "Producto eliminado con Éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    // Creado para mostrar la ultima version
    @GetMapping("/productos/ultima_version/{id}")
    public Version show_lastversion(@PathVariable long id) {
        return productoService.findByIdVersion(id);
    }


}
