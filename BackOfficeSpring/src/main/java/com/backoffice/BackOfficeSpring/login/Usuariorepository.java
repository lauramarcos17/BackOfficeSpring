package com.backoffice.BackOfficeSpring.login;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuariorepository extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByNombreAndContrasena(String nombre, String contrasena);
    Optional<Usuario> findByNombre(String nombre);
}

