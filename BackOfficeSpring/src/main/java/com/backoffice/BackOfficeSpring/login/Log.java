package com.backoffice.BackOfficeSpring.login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@IdClass(LogId.class)
@Table(name="Logs")
public class Log {
    
    @Id
    @Column(name ="usuario")
    private String usuario;

    @Id
    @Column(name = "fechaInicio")
    private String fechaInicio;

    private String fechaFin;
    
    private String operacion;
    private String cliente;
    private String cuaderno;


    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getOperacion() {
        return operacion;
    }
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getCuaderno() {
        return cuaderno;
    }
    public void setCuaderno(String cuaderno) {
        this.cuaderno = cuaderno;
    }

   

}



    