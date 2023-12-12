/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Bravo
 */
public class Censo {

    private Integer id;
    private String nombre;
    private Integer edad;
    private String estado;
    private Date fecha;
    private String estadoEmocional;
    private Censador censador;
    private String motivo;

    public Censo(Integer id, String nombre, Integer edad, String estado, Date fecha, String estadoEmocional, Censador censador, String motivo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.estado = estado;
        this.fecha = fecha;
        this.estadoEmocional = estadoEmocional;
        this.censador = censador;
        this.motivo = motivo;
    }

    public Censo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoEmocional() {
        return estadoEmocional;
    }

    public void setEstadoEmocional(String estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public Censador getCensador() {
        return censador;
    }

    public void setCensador(Censador censador) {
        this.censador = censador;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {

        return "Censo{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", edad=" + edad
                + ", estado='" + estado + '\''
                + ", fecha=" + fecha
                + ", estadoEmocional='" + estadoEmocional + '\''
                + ", censador=" + ((censador != null) ? censador.toString() : "null")
                + ", motivo='" + motivo + '\''
                + '}';
    }

}
