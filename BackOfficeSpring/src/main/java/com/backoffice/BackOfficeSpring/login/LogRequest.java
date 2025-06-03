package com.backoffice.BackOfficeSpring.login;


public class LogRequest { //Esto es el DTO
    
    private int id;   
    private String fechaInicio;
    private String fechaFin;
    private String usuario;
    private String operacion;
    private String cuaderno;
    private String cliente;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getOperacion() {
        return operacion;
    }
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    public String getCuaderno() {
        return cuaderno;
    }
    public void setCuaderno(String cuaderno) {
        this.cuaderno = cuaderno;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    
}
