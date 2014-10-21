package indumatics_plantapintura.data.clases;

import indumatics_plantapintura.data.providers.RutaDP;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Cliente {

    private int idcliente;
    private String razon_social;
    private String direccion;
    private String telefonos;
    private String cp;
    private String localidad;
    private String provincia;
    private String pais;
    private String fac_nombre;
    private String fac_direccion;
    private String fac_cuit;
    private int idtransporte;
    private String web;
    private String mail;
    private String comentarios;
    private double por_fac;
    private double iva;
    private double descuento;
    private int color;
    private double precio_natural;
    private double precio_blanco;
    private double precio_color;
    private boolean sel;
    private Date fua;
    private Date fi;
    private int ruta;
    private String aux1;
    private String aux2;
    private double des_natural;
    private double des_blanco;
    private double des_color;
    private Ruta rutaObj;

    public Cliente() {
    }

    public Cliente(int idcliente, String razon_social, String direccion, String telefonos, String cp, String localidad, String provincia, String pais, String fac_nombre, String fac_direccion, String fac_cuit, int idtransporte, String web, String mail, String comentarios, double por_fac, double iva, double descuento, int color, double precio_natural, double precio_blanco, double precio_color, boolean sel, Date fua, Date fi, int ruta, String aux1, String aux2, double des_natural, double des_blanco, double des_color) {
        this.idcliente = idcliente;
        this.razon_social = razon_social;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.fac_nombre = fac_nombre;
        this.fac_direccion = fac_direccion;
        this.fac_cuit = fac_cuit;
        this.idtransporte = idtransporte;
        this.web = web;
        this.mail = mail;
        this.comentarios = comentarios;
        this.por_fac = por_fac;
        this.iva = iva;
        this.descuento = descuento;
        this.color = color;
        this.precio_natural = precio_natural;
        this.precio_blanco = precio_blanco;
        this.precio_color = precio_color;
        this.sel = sel;
        this.fua = fua;
        this.fi = fi;
        this.setRuta(ruta);
        this.aux1 = aux1;
        this.aux2 = aux2;
        this.des_natural = des_natural;
        this.des_blanco = des_blanco;
        this.des_color = des_color;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFac_nombre() {
        return fac_nombre;
    }

    public void setFac_nombre(String fac_nombre) {
        this.fac_nombre = fac_nombre;
    }

    public String getFac_direccion() {
        return fac_direccion;
    }

    public void setFac_direccion(String fac_direccion) {
        this.fac_direccion = fac_direccion;
    }

    public String getFac_cuit() {
        return fac_cuit;
    }

    public void setFac_cuit(String fac_cuit) {
        this.fac_cuit = fac_cuit;
    }

    public int getIdtransporte() {
        return idtransporte;
    }

    public void setIdtransporte(int idtransporte) {
        this.idtransporte = idtransporte;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getPor_fac() {
        return por_fac;
    }

    public void setPor_fac(double por_fac) {
        this.por_fac = por_fac;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getPrecio_natural() {
        return precio_natural;
    }

    public void setPrecio_natural(double precio_natural) {
        this.precio_natural = precio_natural;
    }

    public double getPrecio_blanco() {
        return precio_blanco;
    }

    public void setPrecio_blanco(double precio_blanco) {
        this.precio_blanco = precio_blanco;
    }

    public double getPrecio_color() {
        return precio_color;
    }

    public void setPrecio_color(double precio_color) {
        this.precio_color = precio_color;
    }

    public boolean isSel() {
        return sel;
    }

    public void setSel(boolean sel) {
        this.sel = sel;
    }

    public Date getFua() {
        return fua;
    }

    public void setFua(Date fua) {
        this.fua = fua;
    }

    public Date getFi() {
        return fi;
    }

    public void setFi(Date fi) {
        this.fi = fi;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
        try {
            setRutaObj(RutaDP.getOne(ruta));
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ruta getRutaObj() {
        return rutaObj;
    }
    
    public void setRutaObj(Ruta ruta){
        this.rutaObj = ruta;
    }
    

    public String getAux1() {
        return aux1;
    }

    public void setAux1(String aux1) {
        this.aux1 = aux1;
    }

    public String getAux2() {
        return aux2;
    }

    public void setAux2(String aux2) {
        this.aux2 = aux2;
    }

    public double getDes_natural() {
        return des_natural;
    }

    public void setDes_natural(double des_natural) {
        this.des_natural = des_natural;
    }

    public double getDes_blanco() {
        return des_blanco;
    }

    public void setDes_blanco(double des_blanco) {
        this.des_blanco = des_blanco;
    }

    public double getDes_color() {
        return des_color;
    }

    public void setDes_color(double des_color) {
        this.des_color = des_color;
    }

    @Override
    public String toString() {
        return razon_social;
    }

}
