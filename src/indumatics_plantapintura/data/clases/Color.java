package indumatics_plantapintura.data.clases;

import java.sql.Date;

public class Color {
    private int id;
    private String color;
    private float precio;
    private double incremento;
    private String comentarios;
    private Date fua;
    private boolean espintura;
    private int idplanta;

    public Color() {
    }

    public Color(int id, String color, float precio, double incremento, String comentarios, Date fua, boolean espintura, int idplanta) {
        this.id = id;
        this.color = color;
        this.precio = precio;
        this.incremento = incremento;
        this.comentarios = comentarios;
        this.fua = fua;
        this.espintura = espintura;
        this.idplanta = idplanta;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public double getIncremento() {
        return incremento;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Date getFua() {
        return fua;
    }

    public void setFua(Date fua) {
        this.fua = fua;
    }

    public boolean isEspintura() {
        return espintura;
    }

    public void setEspintura(boolean espintura) {
        this.espintura = espintura;
    }

    public int getIdplanta() {
        return idplanta;
    }

    public void setIdplanta(int idplanta) {
        this.idplanta = idplanta;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
    
    
    
    
   
    
    
}
