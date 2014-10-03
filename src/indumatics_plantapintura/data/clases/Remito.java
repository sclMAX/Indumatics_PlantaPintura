package indumatics_plantapintura.data.clases;

import java.sql.Date;

public class Remito {
    
    private int idremito;
    private int idcliente;
    private int tipodoc;
    private Date fecha;
    private Date fechaentrega;
    private int transporte;
    private String comentarios;
    private boolean chequeado;
    private boolean precesado;
    private boolean entregado;
    private boolean stockpro;
    private boolean sel;
    private String aux1;
    private String aux2;
    private boolean pagado;
    private int cant_pack;
    private double peso_pack;

    public Remito() {
    }

    public Remito(int idremito, int idcliente, int tipodoc, Date fecha, Date fechaentrega) {
        this.idremito = idremito;
        this.idcliente = idcliente;
        this.tipodoc = tipodoc;
        this.fecha = fecha;
        this.fechaentrega = fechaentrega;
    }

    public int getIdremito() {
        return idremito;
    }

    public void setIdremito(int idremito) {
        this.idremito = idremito;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(int tipodoc) {
        this.tipodoc = tipodoc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public int getTransporte() {
        return transporte;
    }

    public void setTransporte(int transporte) {
        this.transporte = transporte;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isChequeado() {
        return chequeado;
    }

    public void setChequeado(boolean chequeado) {
        this.chequeado = chequeado;
    }

    public boolean isPrecesado() {
        return precesado;
    }

    public void setPrecesado(boolean precesado) {
        this.precesado = precesado;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isStockpro() {
        return stockpro;
    }

    public void setStockpro(boolean stockpro) {
        this.stockpro = stockpro;
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

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public int getCant_pack() {
        return cant_pack;
    }

    public void setCant_pack(int cant_pack) {
        this.cant_pack = cant_pack;
    }

    public double getPeso_pack() {
        return peso_pack;
    }

    public void setPeso_pack(double peso_pack) {
        this.peso_pack = peso_pack;
    }

    @Override
    public String toString() {
        return "Remito{" + "idremito=" + idremito + ", idcliente=" + idcliente + ", tipodoc=" + tipodoc + ", fecha=" + fecha + ", fechaentrega=" + fechaentrega + ", transporte=" + transporte + ", comentarios=" + comentarios + ", chequeado=" + chequeado + ", precesado=" + precesado + ", entregado=" + entregado + ", stockpro=" + stockpro + ", sel=" + sel + ", aux1=" + aux1 + ", aux2=" + aux2 + ", pagado=" + pagado + ", cant_pack=" + cant_pack + ", peso_pack=" + peso_pack + '}';
    }
    
    
    
    
    
    
}
