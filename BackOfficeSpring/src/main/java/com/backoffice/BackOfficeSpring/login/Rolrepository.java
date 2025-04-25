package com.backoffice.BackOfficeSpring.login;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Rolrepository extends JpaRepository<Rol, Integer>{

    Optional<Rol> findById(int id);
}

