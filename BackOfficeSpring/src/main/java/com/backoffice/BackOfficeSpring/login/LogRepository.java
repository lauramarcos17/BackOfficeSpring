package com.backoffice.BackOfficeSpring.login;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import jakarta.transaction.Transactional;

public interface LogRepository  extends JpaRepository <Log, Integer>{ //String?? Por PK compuesta? -> clave es LogId

    List<Log> findByUsuario(String usuario);
     List<Log> findByCliente(String cliente);

    @Transactional
    @Modifying
    void deleteById(int id);
    

}
