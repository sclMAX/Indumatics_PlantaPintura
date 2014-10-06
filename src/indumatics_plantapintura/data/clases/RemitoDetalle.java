package indumatics_plantapintura.data.clases;

public class RemitoDetalle {
    
    private int id;
    private int idremito;
    private int cantidad;
    private String idperfil;
    private int largo;
    private int color;
    private double unidades;
    private double precio;
    private double descuento;
    private boolean procesado;
    private boolean rstock;
    private boolean embalado;
    private boolean act_stock;
    private String comentarios;

    public RemitoDetalle() {
    }

    public RemitoDetalle(int id, int idremito, int cantidad, String idperfil, int largo, int color, double unidades) {
        this.id = id;
        this.idremito = idremito;
        this.cantidad = cantidad;
        this.idperfil = idperfil;
        this.largo = largo;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdremito() {
        return idremito;
    }

    public void setIdremito(int idremito) {
        this.idremito = idremito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(String idperfil) {
        this.idperfil = idperfil;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getUnidades() {
        return unidades;
    }

    public void setUnidades(double unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    public boolean isRstock() {
        return rstock;
    }

    public void setRstock(boolean rstock) {
        this.rstock = rstock;
    }

    public boolean isEmbalado() {
        return embalado;
    }

    public void setEmbalado(boolean embalado) {
        this.embalado = embalado;
    }

    public boolean isAct_stock() {
        return act_stock;
    }

    public void setAct_stock(boolean act_stock) {
        this.act_stock = act_stock;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "RemitoDetalle{" + "id=" + id + ", idremito=" + idremito + ", cantidad=" + cantidad + ", idperfil=" + idperfil + ", largo=" + largo + ", color=" + color + ", unidades=" + unidades + ", precio=" + precio + ", descuento=" + descuento + ", procesado=" + procesado + ", rstock=" + rstock + ", embalado=" + embalado + ", act_stock=" + act_stock + ", comentarios=" + comentarios + '}';
    }
    
    
    
    
    
}
