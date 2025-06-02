package com.backoffice.BackOfficeSpring.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @Autowired
    private MigracionRepository migracionRepository;
    @Autowired
    private LogRepository logRepository;
    
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
        public ResponseEntity<Backup> generarCopiaSeguridad(@RequestParam("id") String idCliente,@RequestParam("descripcionCopia") String descripcionCopia) {
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
                backup.setDescripcion(descripcionCopia); //le metemos descripción que recibe por parámetro
                backupRepository.save(backup);    //guardamos en base de datos
                
                return ResponseEntity.ok(backup);//mando la info 

            } catch (Exception e) {
                System.err.println("Error al obtener datos: " + e.getMessage());
                return ResponseEntity.status(500).body(null);
            }
            
    }


    @GetMapping("/backups")
    public ResponseEntity<List<Backup>> getAllBackups(@RequestParam String cliente) {
        
        List<Backup> backups = backupRepository.findAll();
         System.out.println("ID recibido:*************************************************** " + cliente);
         List<Backup> backups2= backupRepository.findByCliente(cliente);
       
        return ResponseEntity.ok(backups2);
    }



    @DeleteMapping("/eliminarCopia")
    public ResponseEntity<String> eliminarCopia(
       @RequestParam("cliente") String cliente,
       @RequestParam("fechaHora") String fechaHora) {
       try {
          System.out.println("cliente=" + cliente + ", fechaHora=" + fechaHora);
           backupRepository.deleteByClienteAndFechaHora(cliente, fechaHora);
             return ResponseEntity.ok("Copia eliminada correctamente");
         } catch (Exception e) {
             return ResponseEntity.status(500).body("Error al eliminar la copia: " + e.getMessage());
         }
     } 
     
     
     @GetMapping("/restaurarCopia")
        public ResponseEntity<Backup> restaurarCopiaSeguridad(@RequestParam("id") String idCliente) {
            String url = "http://backoffice.practicas/awj-back/backoffice/api/restaura_copia_seguridad?id_cliente=" + idCliente;
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
                 return ResponseEntity.ok(backup);
            } catch (Exception e) {
                System.err.println("Error al obtener datos: " + e.getMessage());
                return ResponseEntity.status(500).body(null);
            }
           
    }

    @GetMapping("/generarMigracion")
        public ResponseEntity<Migracion> generarMigracion(@RequestParam("clienteOrigen") String clienteOrigen ,@RequestParam("clienteDestino") String clienteDestino) {
            String url = "http://backoffice.practicas/awj-back/backoffice/api/crea_migracion?id_cliente_origen=" + clienteOrigen+ "&id_cliente_destino=" + clienteDestino;
            
             
            try{
                RestTemplate restTemplate = new RestTemplate();
              //  String rawJson = restTemplate.getForObject(url, String.class);
               // System.out.println("JSON recibido: " + rawJson);

                //Obtenemos la respuesta parseada como objeto tipo migracion para luego obtener sus campos 
               // respuesta= restTemplate.getForObject(url, MigracionRequest.class); 
                
                 MigracionRequest respuesta = restTemplate.getForObject(url, MigracionRequest.class);
               
                Migracion migracion=new Migracion();
                migracion.setClienteOrigen(respuesta.getClienteOrigen());
                migracion.setClienteDestino(respuesta.getClienteDestino());
                migracion.setFechaHoraInicioOperacion(respuesta.getFechaHoraInicioOperacion());
                migracion.setFechaHoraFinOperacion(respuesta.getFechaHoraFinOperacion());
                migracion.setOperacion(respuesta.getOperacion());
                migracion.setResultado(respuesta.getResultado());
                migracion.setDescripcion(respuesta.getDescripcion());
                migracionRepository.save(migracion);
                return ResponseEntity.ok(migracion);    //guardamos en base de datos

            } catch (Exception e) {
                System.err.println("Error al obtener datos: " + e.getMessage());
                return ResponseEntity.status(500).body(null);
            }
            
    }

    @GetMapping("/migraciones")
    public ResponseEntity<List<Migracion>> getMigraciones() {
        List<Migracion> migraciones = migracionRepository.findAll();
        return ResponseEntity.ok(migraciones);
    }


    ///eliminarMigracion
    @DeleteMapping("/eliminarMigracion")
    public ResponseEntity<String> eliminarMigracion(
       @RequestParam("clienteOrigen") String clienteOrigen,
       @RequestParam("fechaHoraInicioOperacion") String fechaHoraInicioOperacion) {
       try {
          System.out.println("cliente=" + clienteOrigen + ", fechaHora=" + fechaHoraInicioOperacion);
           migracionRepository.deleteByClienteOrigenAndFechaHoraInicioOperacion(clienteOrigen, fechaHoraInicioOperacion);
             return ResponseEntity.ok("Migracion eliminada correctamente");
         } catch (Exception e) {
             return ResponseEntity.status(500).body("Error al eliminar la copia: " + e.getMessage());
         }
     }
     
     
      @GetMapping("/restaurarMigracion")
        public ResponseEntity<String> restaurarMigracion(@RequestParam("clienteOrigen") String clienteOrigen, @RequestParam("clienteDestino") String clienteDestino) {
            String url = "http://backoffice.practicas/awj-back/backoffice/api/restaura_migracion?id_cliente_origen=" + clienteOrigen+"&id_cliente_destino="+clienteDestino;
            MigracionRequest respuesta;
             
            try{
                RestTemplate restTemplate = new RestTemplate();
                String rawJson = restTemplate.getForObject(url, String.class);
                System.out.println("JSON recibido: " + rawJson);

                //Obtenemos la respuesta parseada como objeto tipo backup para luego obtener sus campos 
                respuesta= restTemplate.getForObject(url, MigracionRequest.class); 
                Migracion migracion = new Migracion();
                migracion.setClienteOrigen(respuesta.getClienteOrigen());
                migracion.setClienteDestino(respuesta.getClienteDestino());
                migracion.setFechaHoraInicioOperacion(respuesta.getFechaHoraInicioOperacion());
                migracion.setFechaHoraFinOperacion(respuesta.getFechaHoraFinOperacion());
                migracion.setOperacion(respuesta.getOperacion());
                migracion.setResultado(respuesta.getResultado());
                migracion.setDescripcion(respuesta.getDescripcion());
                migracionRepository.save(migracion);    //guardamos en base de datos

            } catch (Exception e) {
                System.err.println("Error al obtener datos: " + e.getMessage());
                return ResponseEntity.status(500).body("Error al generar migración: " + e.getMessage());
            }
            return ResponseEntity.ok("Migración generada y guardada correctamente.");
    }

    @PostMapping("/generarLog")
    public ResponseEntity<Log> generarLog(@RequestBody Log log) {
        Log savedLog = logRepository.save(log);
        return ResponseEntity.ok(savedLog);
    }

    @GetMapping("/logs")
    public ResponseEntity<List<Log>> getAllLogs(@RequestParam String cliente) {
        System.out.println("ID recibido:*************************************************** " + cliente);
        List<Log> logs = logRepository.findByCliente(cliente);
        return ResponseEntity.ok(logs);
    }
    
    
}











