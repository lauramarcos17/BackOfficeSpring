package com.backoffice.BackOfficeSpring.login;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;

public class BackupId implements Serializable {
    
    @Column(name ="cliente")
    private String cliente;

    private String fechaHora;

    // equals y hashCode obligatorios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BackupId)) return false;
        BackupId that = (BackupId) o;
        return cliente.equals(that.cliente) && fechaHora.equals(that.fechaHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, fechaHora);
    }
}