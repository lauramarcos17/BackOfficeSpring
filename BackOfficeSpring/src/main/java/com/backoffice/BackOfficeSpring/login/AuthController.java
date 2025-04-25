package com.backoffice.BackOfficeSpring.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private Usuariorepository usuarioRepository;
    @Autowired
    private Rolrepository rolRepository;
    
    

    @PostMapping("/login") 
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        //Optional<Usuario> usuario = usuarioRepository.findByNombreAndContrasena(
            //loginRequest.getNombre(), encoder.encode(loginRequest.getContrasena()));
            // System.out.println("****PASS ORIGINAL: "+ loginRequest.getContrasena());
            // System.out.println("****PAss HASHEADA: " + encoder.encode(loginRequest.getContrasena()));
        
            Optional<Usuario> usuarioPorNombre = usuarioRepository.findByNombre(loginRequest.getNombre());

            
        
        Map<String, Object> response = new HashMap<>();
        //response.put("success", usuario.isPresent());
        if (usuarioPorNombre.isPresent()) {
            Usuario usuarioBD = usuarioPorNombre.get();
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

    /*
     * Antes de eliminar findByNombreAndContrasena
     *     @PostMapping("/login") 
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreAndContrasena(
            loginRequest.getNombre(), encoder.encode(loginRequest.getContrasena()));
            System.out.println("****PASS ORIGINAL: "+ loginRequest.getContrasena());
            System.out.println("****PAss HASHEADA: " + encoder.encode(loginRequest.getContrasena()));
        
            Optional<Usuario> usuarioPorNombre = usuarioRepository.findByNombre(loginRequest.getNombre());
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", usuario.isPresent());
        if (!usuario.isPresent() && usuarioPorNombre.isEmpty() ) {
            response.put("errorMsg", "Usuario no encontrado");
        }else{
            response.put("errorMsg", "Contraseña incorrecta");
        }
            
        
        response.put("errorMessage", usuario.isPresent());
        return ResponseEntity.ok(response);
    }
     * 
     * 
     */

    
}

