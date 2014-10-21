package indumatics_plantapintura.data.clases;

import indumatics_plantapintura.data.providers.OrdenPinturaDetalleDP;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdenPintura {

    private int nro;
    private int idproveedor;
    private Date fecha;
    private Date fechaentrega;
    private String comentarios;
    private boolean procesado;
    private Set<OrdenPinturaDetalle> detalles = new HashSet<>();

    public OrdenPintura(int nro, int idproveedor, Date fecha, Date fechaentrega) {
        this.nro = nro;
        this.idproveedor = idproveedor;
        this.fecha = fecha;
        this.fechaentrega = fechaentrega;
    }

    public OrdenPintura() {
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro)  {
        this.nro = nro;
        try {
            this.setDetalles(OrdenPinturaDetalleDP.getDetalleOrden(this));
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPintura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }
    
    public void addDetalle(OrdenPinturaDetalle detalle){
        this.detalles.add(detalle);
    }
    
    public void removeDetalle (OrdenPinturaDetalle detalle){
        this.detalles.remove(detalle);
    }
    
    public Set<OrdenPinturaDetalle> getDetalles(){
        return detalles;
    }
    
    public void setDetalles(Set<OrdenPinturaDetalle> detalles){
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return new DecimalFormat("000000000000").format(nro);
    }
}
