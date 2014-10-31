package indumatics_plantapintura.data.clases;

import java.sql.Date;

public class Perfil {

    private String idperf;
    private String codproveedor;
    private String descripcion;
    private int largo;
    private double pxm;
    private int bxp;
    private Object image;
    private int idproveedor;
    private double adicional;
    private double preciocompra;
    private double utilidad;
    private double precioventa;
    private boolean disponible;
    private int idlinea;
    private boolean sel;
    private String aux1;
    private String aux2;
    private Date fua;
    private boolean act_stock;
    private int mpp;
    private int idlista;
    private int rubro;

    public Perfil() {
    }

    public Perfil(String idperf, String codproveedor, String descripcion, int largo, double pxm, int idproveedor, int idlista, int rubro) {
        this.idperf = idperf;
        this.codproveedor = codproveedor;
        this.descripcion = descripcion;
        this.largo = largo;
        this.pxm = pxm;
        this.idproveedor = idproveedor;
        this.idlista = idlista;
        this.rubro = rubro;
    }

    public String getIdperf() {
        return idperf;
    }

    public void setIdperf(String idperf) {
        this.idperf = idperf;
    }

    public String getCodproveedor() {
        return codproveedor;
    }

    public void setCodproveedor(String codproveedor) {
        this.codproveedor = codproveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public double getPxm() {
        return pxm;
    }

    public void setPxm(double pxm) {
        this.pxm = pxm;
    }

    public int getBxp() {
        return bxp;
    }

    public void setBxp(int bxp) {
        this.bxp = bxp;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public double getAdicional() {
        return adicional;
    }

    public void setAdicional(double adicional) {
        this.adicional = adicional;
    }

    public double getPreciocompra() {
        return preciocompra;
    }

    public void setPreciocompra(double preciocompra) {
        this.preciocompra = preciocompra;
    }

    public double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getIdlinea() {
        return idlinea;
    }

    public void setIdlinea(int idlinea) {
        this.idlinea = idlinea;
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

    public Date getFua() {
        return fua;
    }

    public void setFua(Date fua) {
        this.fua = fua;
    }

    public boolean isAct_stock() {
        return act_stock;
    }

    public void setAct_stock(boolean act_stock) {
        this.act_stock = act_stock;
    }

    public int getMpp() {
        return mpp;
    }

    public void setMpp(int mpp) {
        this.mpp = mpp;
    }

    public int getIdlista() {
        return idlista;
    }

    public void setIdlista(int idlista) {
        this.idlista = idlista;
    }

    public int getRubro() {
        return rubro;
    }

    public void setRubro(int rubro) {
        this.rubro = rubro;
    }

    @Override
    public String toString() {
        return idperf;
    }
}
