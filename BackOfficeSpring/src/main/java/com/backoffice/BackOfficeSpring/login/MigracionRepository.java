package com.backoffice.BackOfficeSpring.login;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import jakarta.transaction.Transactional;

public interface MigracionRepository  extends JpaRepository <Migracion, MigracionId>{ //String?? Por PK compuesta? -> clave es MIGRACIONID

    List<Migracion> findByClienteOrigen(String clienteOrigen);

    @Transactional
    @Modifying
    void deleteByClienteOrigenAndFechaHoraInicioOperacion(String clienteOrigen, String fechaHoraInicioOperacion);
    

}
