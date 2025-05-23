package com.backoffice.BackOfficeSpring.login;


public class MigracionRequest { //Esto es el DTO
    
     private String clienteOrigen;
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
    public String getFechaHoraInicioOperacion() {
        return fechaHoraInicioOperacion;
    }
    public void setFechaHoraInicioOperacion(String fechaHoraInicioOperacion) {
        this.fechaHoraInicioOperacion = fechaHoraInicioOperacion;
    }
    public String getClienteDestino() {
        return clienteDestino;
    }
    public void setClienteDestino(String clienteDestino) {
        this.clienteDestino = clienteDestino;
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
