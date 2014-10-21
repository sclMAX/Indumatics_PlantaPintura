package indumatics_plantapintura.data.clases;

public class Ruta {
    private int id;
    private String ruta;
    private String descripcion;
    private String dias;
    private double incremento;

    public Ruta() {
    }

    public Ruta(int id, String ruta, String descripcion, String dias, double incremento) {
        this.id = id;
        this.ruta = ruta;
        this.descripcion = descripcion;
        this.dias = dias;
        this.incremento = incremento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public double getIncremento() {
        return incremento;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }

    @Override
    public String toString() {
        return ruta;
    }
    
    
}
