package com.backoffice.BackOfficeSpring.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class AuthController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private Usuariorepository usuarioRepository;
    @Autowired
    private Rolrepository rolRepository;
    @Autowired
    private BackupRepository backupRepository;
    
    @PostMapping("/login") 
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
       
        // Buscamos en la base de datos por lo introducido en el login (recuperamos todos los campos )
            Optional<Usuario> usuarioPorNombre = usuarioRepository.findByNombre(loginRequest.getNombre());

        Map<String, Object> response = new HashMap<>();
        
        if (usuarioPorNombre.isPresent()) {
            Usuario usuarioBD = usuarioPorNombre.get(); //obtenemos todos los campos 
            boolean passwordMatch = encoder.matches(loginRequest.getContrasena(), usuarioBD.getContrasena());

            if (passwordMatch){
                response.put("success", true);
                response.put("errorMsg", "");
                Optional<Rol> rolPorId = rolRepository.findById(usuarioBD.getRol());
                Rol rolBD=rolPorId.get();
                
                response.put("rolnombre",rolBD.getNombre());
                response.put("rol", usuarioBD.getRol());
                

            }else{
                response.put("success", false);
                response.put("errorMsg", "Contraseña incorrecta");
            }
        
        }else{
            response.put("success", false);
            response.put("errorMsg", "Usuario no encontrado");
        }
        return ResponseEntity.ok(response);
    }

    

    @GetMapping("/clientes")
    public ResponseEntity<String> obtenerCliente(@RequestParam("id") int idCliente) {
        String url = "http://backoffice.practicas/awj-back/backoffice/api/clientes?id_cliente=" + idCliente;
        String respuesta;
        try{
        RestTemplate restTemplate = new RestTemplate();
        respuesta= restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
        System.err.println("Error al obtener datos: " + e.getMessage());
        return ResponseEntity.status(500).body("Error al obtener cliente: " + e.getMessage());
        }
        return ResponseEntity.ok(respuesta);
    }

      @GetMapping("/generarCopiaSeguridad")
        public ResponseEntity<String> generarCopiaSeguridad(@RequestParam("id") String idCliente) {
            String url = "http://backoffice.practicas/awj-back/backoffice/api/genera_copia_seguridad?id_cliente=" + idCliente;
            BackupRequest respuesta;
             
            try{
                RestTemplate restTemplate = new RestTemplate();
                String rawJson = restTemplate.getForObject(url, String.class);
                System.out.println("JSON recibido: " + rawJson);

                //Obtenemos la respuesta parseada como objeto tipo backup para luego obtener sus campos 
                respuesta= restTemplate.getForObject(url, BackupRequest.class); 
                Backup backup = new Backup();
                backup.setCliente(respuesta.getCliente());
                backup.setFechaHora(respuesta.getFechaHora());
                backup.setDescripcion(respuesta.getDescripcion());
                backupRepository.save(backup);    //guardamos en base de datos

            } catch (Exception e) {
                System.err.println("Error al obtener datos: " + e.getMessage());
                return ResponseEntity.status(500).body("Error al generar copia: " + e.getMessage());
            }
            return ResponseEntity.ok("Copia de seguridad generada y guardada correctamente.");
    }


    @GetMapping("/backups")
    public ResponseEntity<List<Backup>> getAllBackups(@RequestParam String cliente) {
        
        List<Backup> backups = backupRepository.findAll();
         System.out.println("ID recibido:*************************************************** " + cliente);
         List<Backup> backups2= backupRepository.findByCliente(cliente);
       
        return ResponseEntity.ok(backups2);
    }
}






      

    


