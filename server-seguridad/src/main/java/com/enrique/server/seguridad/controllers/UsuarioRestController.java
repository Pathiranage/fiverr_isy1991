package com.enrique.server.seguridad.controllers;

import com.enrique.server.seguridad.models.entity.Rol;
import com.enrique.server.seguridad.models.entity.Usuario;
import com.enrique.server.seguridad.services.IRolService;
import com.enrique.server.seguridad.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //@Secured("ROLE_ADMIN")
    @GetMapping("/usuarios")
    public List<Usuario> index() {
        this.findUsers("ROLE_USER");
        return usuarioService.findAll();

    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Usuario usuario = usuarioService.findById(id);

        Map<String, Object> response = new HashMap<>();

        if (usuario == null) {
            response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    // Devuelve el cliente creado, recibirá el objeto cliente en JSON
    //@Secured("ROLE_ADMIN")
    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Usuario usuario, Rol rol) {

        Usuario usuarioNew = null;
        Map<String, Object> response = new HashMap<>();

        List<Rol> roles;
        List<Rol> rol_unico;

        roles = rolService.findAll();
        System.out.println(roles.size());

        roles.remove(1);


        try {
            usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
            usuario.setRoles(roles);


            usuarioNew = usuarioService.save(usuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Usuario ha sido creado");
        response.put("usuario", usuarioNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    // Empleado para actualizar, no como el POST
    //@Secured("ROLE_ADMIN")
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {


        Usuario usuarioActual = usuarioService.findById(id);

        Usuario usuarioUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (usuarioActual == null) {
            response.put("mensaje", "Error: no se pudo editar ya que no existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            usuarioActual.setApellido(usuario.getApellido());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setUsername(usuario.getUsername());
            usuarioActual.setRoles(usuario.getRoles());

            usuarioActual.setPassword(this.passwordEncoder.encode(usuario.getPassword()));


            usuarioUpdated = usuarioService.save(usuarioActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Usuario actualizado con exito");
        response.put("usuarios", usuarioUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // Eliminamos el cliente
    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        usuarioService.delete(id);

        response.put("mensaje", "Usuario eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    /*
    @GetMapping("/evaluaciones_usuarios")
    public List<Evaluacion> index(HttpServletRequest request){
        String[] token=request.getHeader("Authorization").split(" ");
        String[] parts = token[1].split("\\.");
        System.out.println("----------------------------------------");
        Base64 decodeToken = new Base64(true);
        String header = parts[0];
        String body = parts[1];
        String signature = parts[2];
        System.out.println(header);
        System.out.println(body);
        System.out.println(signature);
        String jwt = new String(decodeToken.decode(body));
        System.out.println(jwt);
        JSONObject json = new JSONObject(jwt);
        System.out.println(json.get("id").toString());

        int total_evaluaciones= Integer.parseInt(json.get("id").toString());
        System.out.println(total_evaluaciones);

        return usuarioService.selectAllByIdUser(Integer.parseInt(json.get("id").toString()));
    }
    */
    @GetMapping("/usuarios-by-role")
    public ResponseEntity findUsers(@RequestParam String role) {
        if (role == null || role.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Usuario> usuarios = this.usuarioService.findUsersByRole(role);
        return ResponseEntity.ok(usuarios);
    }
}

