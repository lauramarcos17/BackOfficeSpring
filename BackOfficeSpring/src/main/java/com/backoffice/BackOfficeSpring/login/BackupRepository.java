package com.backoffice.BackOfficeSpring.login;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupRepository  extends JpaRepository <Backup, BackupId>{ //String?? Por PK compuesta? -> clave es BackupId

    List<Backup> findByCliente(String cliente);
    

}
