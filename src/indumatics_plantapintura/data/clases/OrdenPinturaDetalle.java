package indumatics_plantapintura.data.clases;

import indumatics_plantapintura.data.providers.ColorDP;
import indumatics_plantapintura.data.providers.PerfilDP;
import indumatics_plantapintura.data.providers.StockDP;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdenPinturaDetalle {

    private int id;
    private int idOrden;
    private int cantidad;
    private String idPerfil;
    private int colorH;
    private int colorD;
    private String comentarios;
    private boolean proc;
    private boolean pintado;
    private boolean stk_Act;
    private boolean act_Stk;
    private int largo;
    private Perfil perfil;
    private Color colorHorigen;
    private Color colorDestino;

    public OrdenPinturaDetalle(int id, int idOrden, int cantidad, String idPerfil, int colorH, int colorD) {
        this.id = id;
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.setIdPerfil(idPerfil);
        this.setColorH(colorH);
        this.setColorD(colorD);
    }

    public OrdenPinturaDetalle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
        try {
            this.perfil = PerfilDP.getOne(idPerfil);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            this.perfil = null;
        }
    }

    public int getColorH() {
        return colorH;
    }

    public void setColorH(int colorH) {
        this.colorH = colorH;
        try {
            this.colorHorigen =ColorDP.getOne(colorH);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getColorD() {
        return colorD;
    }

    public void setColorD(int colorD) {
        this.colorD = colorD;
        try {
            this.colorDestino = ColorDP.getOne(colorD);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isProc() {
        return proc;
    }

    public void setProc(boolean proc) {
        this.proc = proc;
    }

    public boolean isPintado() {
        return pintado;
    }

    public void setPintado(boolean pintado) {
        this.pintado = pintado;
    }

    public boolean isStk_Act() {
        return stk_Act;
    }

    public void setStk_Act(boolean stk_Act) {
        this.stk_Act = stk_Act;
    }

    public boolean isAct_Stk() {
        return act_Stk;
    }

    public void setAct_Stk(boolean act_Stk) {
        this.act_Stk = act_Stk;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Color getColorHorigen() {
        return colorHorigen;
    }

    public void setColorHorigen(Color colorHorigen) {
        this.colorHorigen = colorHorigen;
    }

    public Color getColorDestino() {
        return colorDestino;
    }

    public void setColorDestino(Color colorDestino) {
        this.colorDestino = colorDestino;
    }

    public int getStockTotal() {
        try {
            return StockDP.getStockTotalPerfil(perfil);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int getStockDisponible() {
        try {
            return StockDP.getStockDisponiblePerfil(perfil);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int getStockColorHorigen(){
        try {
            return StockDP.getStockPerfilColor(perfil, colorHorigen);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int getStockColorDestino(){
        try {
            return StockDP.getStockPerfilColor(perfil, colorDestino);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return Integer.toString(cantidad);
    }


}
