package com.backoffice.BackOfficeSpring.login;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


@RequestMapping("/api")
@CrossOrigin(origins = "*") // permite peticiones desde Angular
public class AuthController {

    @Autowired
    private Usuariorepository usuarioRepository;

    @PostMapping("/login") 

    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByNombreAndContrasena(
            loginRequest.getNombre(), loginRequest.getContrasena());

        Map<String, Object> response = new HashMap<>();
        response.put("success", usuario.isPresent());

        return ResponseEntity.ok(response);
    }
}