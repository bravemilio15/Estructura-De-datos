package modelo;

public class Antena {

    private Integer id;
    private String nombre;
    private Double latitud;
    private Double longitud;
    private String foto;

    public Antena() {
    }

    public Antena(Integer id, String nombre, Double latitud, Double longitud, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.foto = foto;
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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String obtenerNombreNodo() {
        return nombre; 
    }

    @Override
    public String toString() {
        return " Antena: " + nombre + " Latitud: " + latitud + " Longitud: " + longitud;
    }

}
