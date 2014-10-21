package indumatics_plantapintura.data.clases;

import java.sql.Date;

public class Stock {
    private String idPerfil;
    private int color;
    private int stock;
    private Date fecha_UV;
    private Date fecha_UC;
    private String comentarios;
    private int mpp;
    private Date fua;
    private boolean sel;
    private String aux1;
    private String aux2;
    private Perfil perfilObj;
    private Color colorObj;

    public Stock() {
    }

    public Stock(String idPerfil, int color, int stock) {
        this.idPerfil = idPerfil;
        this.color = color;
        this.stock = stock;
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFecha_UV() {
        return fecha_UV;
    }

    public void setFecha_UV(Date fecha_UV) {
        this.fecha_UV = fecha_UV;
    }

    public Date getFecha_UC() {
        return fecha_UC;
    }

    public void setFecha_UC(Date fecha_UC) {
        this.fecha_UC = fecha_UC;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getMpp() {
        return mpp;
    }

    public void setMpp(int mpp) {
        this.mpp = mpp;
    }

    public Date getFua() {
        return fua;
    }

    public void setFua(Date fua) {
        this.fua = fua;
    }

    public boolean isSel() {
        return sel;
    }

    public void setSel(boolean sel) {
        this.sel = sel;
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

    public Perfil getPerfilObj() {
        return perfilObj;
    }

    public void setPerfilObj(Perfil perfilObj) {
        this.perfilObj = perfilObj;
    }

    public Color getColorObj() {
        return colorObj;
    }

    public void setColorObj(Color colorObj) {
        this.colorObj = colorObj;
    }
    
    
}
