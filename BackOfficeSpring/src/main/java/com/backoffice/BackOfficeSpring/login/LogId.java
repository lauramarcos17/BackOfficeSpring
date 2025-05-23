package com.backoffice.BackOfficeSpring.login;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;

public class LogId implements Serializable {
    
    @Column(name ="usuario")
    private String usuario;

    @Column(name ="fechaInicio")
    private String fechaInicio;

    // equals y hashCode obligatorios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogId)) return false;
        LogId that = (LogId) o;
        return usuario.equals(that.usuario) && fechaInicio.equals(that.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, fechaInicio);
    }

  
}