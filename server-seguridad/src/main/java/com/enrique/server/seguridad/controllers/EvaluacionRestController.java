package com.enrique.server.seguridad.controllers;

import com.enrique.server.seguridad.models.entity.Charateristics;
import com.enrique.server.seguridad.models.entity.Evaluacion;
import com.enrique.server.seguridad.models.entity.Macrocharacteristic;
import com.enrique.server.seguridad.models.entity.Usuario;
import com.enrique.server.seguridad.services.IEvaluacionService;
import com.enrique.server.seguridad.services.IUsuarioService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.enrique.server.seguridad.Informe;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EvaluacionRestController {

    @Autowired
    private IEvaluacionService evaluacionService;
    @Autowired
    private IUsuarioService iUsuarioService;

    // Listar evaluaciones para un cliente que inicie sesion
    @GetMapping("/evaluaciones")
    public List<Evaluacion> index(HttpServletRequest request) {

        String[] token = request.getHeader("Authorization").split(" ");
        String[] parts = token[1].split("\\.");
        System.out.println("----------------------------------------");

        Base64 decodeToken = new Base64(true);
        //String header = parts[0];
        String body = parts[1];
        //String signature = parts[2];

        String jwt = new String(decodeToken.decode(body));
        JSONObject json = new JSONObject(jwt);
        //System.out.println(json.get("id").toString());

        String nombre_usuario = json.get("user_name").toString();
        //System.out.println(nombre_usuario);
        //System.out.println(evaluacionService.findAllById(Long.parseLong(json.get("id").toString())).size());

        return evaluacionService.findAllByUserId(Long.parseLong(json.get("id").toString()));
    }

    @GetMapping("/evaluaciones/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Evaluacion evaluacion = evaluacionService.findById(id);

        Map<String, Object> response = new HashMap<>();

        if (evaluacion == null) {
            response.put("mensaje", "La Evaluacion con ID: ".concat(id.toString().concat(" no existe")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(evaluacion, HttpStatus.OK);
    }


    // Devuelve el cliente creado, recibirá el objeto cliente en JSON
    //	@Secured("ROLE_ADMIN")
    @ResponseBody
    @PostMapping("/evaluaciones")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("file not found");
        }

        Map<String, Object> response = new HashMap<>();
        try {
            String extension = ".".concat(FilenameUtils.getExtension(file.getOriginalFilename()));
            System.out.println(extension);
            byte[] bytes = file.getBytes();
            String roothPath = "c:\\temp";
            File dir = new File(roothPath + File.separator + "tmpFiles");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String pathname = dir.getAbsolutePath() + File.separator + "FicheroMetrica" + extension;
            File serveFile = new File(pathname);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serveFile));
            stream.write(bytes);
            stream.close();

            File jsonInputFile = new File(pathname);
            ObjectMapper mapper = new ObjectMapper();
            Evaluacion evaluacion = mapper.readValue(jsonInputFile, Evaluacion.class);
            Evaluacion save = this.evaluacionService.save(evaluacion);
            Macrocharacteristic macrocharacteristic = this.evaluacionService.findMacrocharacteristicById(save.getMacrocharacteristic().getId());
            response.put("mensaje", save);
            response.put("macrocharacteristic", macrocharacteristic);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("file write fail");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Empleado para actualizar, no como el POST
    //@Secured("ROLE_ADMIN")
    @PutMapping("/evaluaciones/{id}")
    public ResponseEntity<?> update(HttpServletRequest request, @RequestBody Evaluacion evaluacion, @PathVariable Long id) {
        String[] token = request.getHeader("Authorization").split(" ");
        String[] parts = token[1].split("\\.");

        Base64 decodeToken = new Base64(true);
        String body = parts[1];

        String jwt = new String(decodeToken.decode(body));
        JSONObject json = new JSONObject(jwt);
        long userId = Long.parseLong(json.get("id").toString());
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        if (evaluacion == null) {
            return ResponseEntity.badRequest().build();
        }

        Evaluacion evaluacionActual = evaluacionService.findById(id);
        if (evaluacionActual == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        evaluacionActual.setProducto(evaluacion.getProducto());
        evaluacionActual.setVersion(evaluacion.getVersion());
        Evaluacion evaluacionUpdated = evaluacionService.save(evaluacionActual);

        List<Usuario> usuarios = new ArrayList<>();
        Usuario byId = iUsuarioService.findById(userId);
        List<Evaluacion> evaluaciones = byId.getEvaluaciones();
        evaluaciones.add(evaluacionActual);
        byId.setEvaluaciones(evaluaciones);
        usuarios.add(byId);

        evaluacion.getUsuarios().forEach(usuario -> {
            Usuario byId1 = iUsuarioService.findById(usuario.getId());
            List<Evaluacion> evaluaciones1 = new ArrayList<>(evaluaciones);
            byId1.setEvaluaciones(evaluaciones1);
            usuarios.add(byId1);
        });

        iUsuarioService.saveAll(usuarios);

        Map<String, Object> response = new HashMap<>();

        response.put("mensaje", "Evaluacion actualizado con exito");
        response.put("Evaluacions", evaluacionUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // Eliminamos el cliente
    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/evaluaciones/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        evaluacionService.delete(id);

        response.put("mensaje", "Evaluacion eliminada con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @GetMapping("/evaluaciones/{id}/charasterictics")
    public ResponseEntity charasteristicsByEvaluacionesId(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        List<Charateristics> charateristics = evaluacionService.charasteristicsByEvaluacionesId(id);
        return ResponseEntity.ok(charateristics);
    }
    /*
    @GetMapping("/evaluaciones/{id}/informe")
    public ResponseEntity createInform(@PathVariable Long id) throws IOException {

    	//Cogemos la evaluacion
        Evaluacion evaluacion = evaluacionService.findById(id);
        
        //Cogemos la macrocaracteristica
        Macrocharacteristic macrocharacteristic = this.evaluacionService.findMacrocharacteristicById(evaluacion.getMacrocharacteristic().getId());
        
        //Cogemos el producto
        Producto producto = evaluacion.getProducto();
        
        
        //Cogemos la empresa
        Empresa empresa = producto.getEmpresa();
        
        //Cogemos las caracteristicas
        List<Charateristics> caracteristicas = evaluacion.getMacrocharacteristic().getCharateristics();
        
        //Cogemos las propiedades
        List<Properties> propiedadescump = caracteristicas.get(0).getProperties();
        List<Properties> propiedadesconf = caracteristicas.get(1).getProperties();
        List<Properties> propiedadestraz = caracteristicas.get(2).getProperties();
        List<Properties> propiedadesdisp = caracteristicas.get(3).getProperties();
        List<Properties> propiedadesrecup = caracteristicas.get(4).getProperties();
        

        
		
		//Informe informe = new Informe(evaluacion, macrocharacteristic, caracteristicas, empresa, producto, propiedadescump, propiedadesconf, propiedadestraz, propiedadesdisp, propiedadesrecup);
		//informe.generarInformes();

		
        
        for(int j=0; j<propiedadescump.size(); j++) {
        	System.out.println(propiedadescump.get(j).getName());
        	System.out.println(propiedadescump.get(j).getValue());
        }
        
        for(int j=0; j<propiedadesconf.size(); j++) {
        	System.out.println(propiedadesconf.get(j).getName());
        	System.out.println(propiedadesconf.get(j).getValue());
        }
        
        for(int j=0; j<propiedadestraz.size(); j++) {
        	System.out.println(propiedadestraz.get(j).getName());
        	System.out.println(propiedadestraz.get(j).getValue());
        }
        
        for(int j=0; j<propiedadesdisp.size(); j++) {
        	System.out.println(propiedadesdisp.get(j).getName());
        	System.out.println(propiedadesdisp.get(j).getValue());
        }
        
        for(int j=0; j<propiedadesrecup.size(); j++) {
        	System.out.println(propiedadesrecup.get(j).getName());
        	System.out.println(propiedadesrecup.get(j).getValue());
        }

        
        /*for(int i=0;i<caracteristicas.size();i++) {
        System.out.println(caracteristicas.get(i).getName());
        System.out.println(caracteristicas.get(i).getValue());
        }
        System.out.println(producto.getNombre());
        System.out.println(macrocharacteristic.getAcronym());
    	System.out.println(id);
    	return ResponseEntity.ok(id);
    }
*/
}
