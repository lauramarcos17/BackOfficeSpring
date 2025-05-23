package com.backoffice.BackOfficeSpring.login;


public class BackupRequest { //Esto es el DTO
    
    private String cliente;
    private String fechaHora;
    private String descripcion;

    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getFechaHora() {
        return fechaHora;
    }
    public void setFecha(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
