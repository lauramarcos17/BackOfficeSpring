package com.backoffice.BackOfficeSpring.login;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;

public class MigracionId implements Serializable {
    
    @Column(name ="clienteOrigen")
    private String clienteOrigen;
   
    private String fechaHoraInicioOperacion;

    // equals y hashCode obligatorios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MigracionId)) return false;
        MigracionId that = (MigracionId) o;
        return clienteOrigen.equals(that.clienteOrigen) && fechaHoraInicioOperacion.equals(that.fechaHoraInicioOperacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteOrigen, fechaHoraInicioOperacion);
    }


}