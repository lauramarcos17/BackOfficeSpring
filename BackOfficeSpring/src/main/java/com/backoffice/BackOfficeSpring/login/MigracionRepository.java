package com.backoffice.BackOfficeSpring.login;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import jakarta.transaction.Transactional;

public interface MigracionRepository  extends JpaRepository <Migracion, Integer>{ //String?? Por PK compuesta? -> clave es MIGRACIONID

    List<Migracion> findByClienteOrigen(String clienteOrigen);
    List<Migracion> findByClienteOrigenOrClienteDestino(String clienteOrigen, String clienteDestino);

    @Transactional
    @Modifying
    void deleteById(int id);
    

}
