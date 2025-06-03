package com.backoffice.BackOfficeSpring.login;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="Migracion2")
public class Migracion{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

 
    @Column(name ="clienteOrigen")
    private String clienteOrigen;
    
   
    
    @Column(name = "fechaHoraInicioOperacion")
    private String fechaHoraInicioOperacion;

    
    private String clienteDestino;
    private String fechaHoraFinOperacion;
    private String operacion;
    private String resultado;
    private String descripcion;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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



    