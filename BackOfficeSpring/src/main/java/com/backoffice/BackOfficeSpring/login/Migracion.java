package com.backoffice.BackOfficeSpring.login;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(MigracionId.class)
@Table(name="Migracion")
public class Migracion{
    
    @Id
    @Column(name ="clienteOrigen")
    private String clienteOrigen;
    
   
    @Id
    @Column(name = "fechaHoraInicioOperacion")
    private String fechaHoraInicioOperacion;

    
    private String clienteDestino;
    private String fechaHoraFinOperacion;
    private String operacion;
    private String resultado;
    private String descripcion;



    public String getClienteOrigen() {
        return clienteOrigen;
    }
    public void setClienteOrigen(String clienteOrigen) {
        this.clienteOrigen = clienteOrigen;
    }
    public String getClienteDestino() {
        return clienteDestino;
    }
    public void setClienteDestino(String clienteDestino) {
        this.clienteDestino = clienteDestino;
    }
    public String getFechaHoraInicioOperacion() {
        return fechaHoraInicioOperacion;
    }
    public void setFechaHoraInicioOperacion(String fechaHoraInicioOperacion) {
        this.fechaHoraInicioOperacion = fechaHoraInicioOperacion;
    }
    public String getFechaHoraFinOperacion() {
        return fechaHoraFinOperacion;
    }
    public void setFechaHoraFinOperacion(String fechaHoraFinOperacion) {
        this.fechaHoraFinOperacion = fechaHoraFinOperacion;
    }
    public String getOperacion() {
        return operacion;
    }
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

  

}



    