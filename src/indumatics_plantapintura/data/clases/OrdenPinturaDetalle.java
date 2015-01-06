package indumatics_plantapintura.data.clases;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.Utils;
import indumatics_plantapintura.data.providers.ColorDP;
import indumatics_plantapintura.data.providers.OrdenPinturaDetalleDP;
import indumatics_plantapintura.data.providers.PerfilDP;
import indumatics_plantapintura.data.providers.StockDP;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdenPinturaDetalle {

    private int id;
    private int idOrden;
    private int cantidad = 0;
    private int aProcesar = 0;
    private String idPerfil = "";
    private int colorO = 0;
    private int colorD = 0;
    private String comentarios = " ";
    private boolean proc = false;
    private boolean pintado = false;
    private boolean stk_Act = false;
    private boolean act_Stk = true;
    private int largo;
    private Perfil perfil;
    private Color colorOrigen;
    private Color colorDestino;
    private int stockTotal = dfv;
    private int stockDisponible = dfv;
    private int stockColorOrigen = dfv;
    private int enProcesoColorOrigen = dfv;
    private int stockColorDestino = dfv;
    private int enProcesoColorDestino = dfv;
    private int stockNatural = dfv;
    private int pedidosNatural = dfv;

    private static final int dfv = 0;

    public OrdenPinturaDetalle(int id, int idOrden, int cantidad, String idPerfil, int colorH, int colorD) {
        this.id = id;
        this.idOrden = idOrden;
        this.cantidad = cantidad;
        this.setIdPerfil(idPerfil);
        this.setColorO(colorH);
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
            Perfil p = PerfilDP.getOne(idPerfil);
            if (p != null) {
                this.perfil = p;
                this.stockTotal = StockDP.getStockTotalPerfil(perfil);
                this.stockDisponible = StockDP.getStockDisponiblePerfil(perfil);
                this.stockNatural = StockDP.getStockPerfilColor(perfil, new Color(ColorDP.ID_NATURAL));
                this.pedidosNatural = StockDP.getPedidosNatural(perfil);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getColorO() {
        return colorO;
    }

    public void setColorO(int colorO) {
        this.colorO = colorO;
        try {
            Color c = ColorDP.getOne(colorO);
            if ((perfil != null) && (c != null)) {
                this.colorOrigen = c;
                this.stockColorOrigen = StockDP.getStockPerfilColor(perfil, c);
                this.enProcesoColorOrigen = OrdenPinturaDetalleDP.getProcesoOrigen(perfil.getIdperf(), c.getId());
            }
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
            Color c = ColorDP.getOne(colorD);
            if ((perfil != null) && (c != null)) {
                this.colorDestino = c;
                this.stockColorDestino = StockDP.getStockPerfilColor(perfil, c);
                this.enProcesoColorDestino = OrdenPinturaDetalleDP.getProcesoDestino(perfil.getIdperf(), c.getId());
            }
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

    public Color getColorOrigen() {
        return colorOrigen;
    }

    public void setColorOrigen(Color colorOrigen) {
        this.colorOrigen = colorOrigen;
        setColorO(colorOrigen.getId());
    }

    public Color getColorDestino() {
        return colorDestino;
    }

    public void setColorDestino(Color colorDestino) {
        this.colorDestino = colorDestino;
        setColorD(colorDestino.getId());
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public int getStockNatural() {
        return stockNatural;
    }

    public int getStockColorOrigen() {
        return stockColorOrigen;
    }

    public int getEnProcesoColorOrigen() {
        return enProcesoColorOrigen;
    }

    public int getStockColorDestino() {
        return stockColorDestino;
    }

    public int getEnProcesoColorDestino() {
        return enProcesoColorDestino;
    }

    public int getPedidosNatural() {
        return pedidosNatural;
    }

    public void validarCantidad() {
        try {
            Stock stk = StockDP.getOne(perfil, colorDestino);
            if (stk != null) {
                int c = 0;
                c = (getCantidad() + stk.getMpp())
                        - (getStockColorDestino() + getEnProcesoColorDestino());
                if (c >= 0) {
                    setaProcesar(c);
                } else {
                    setaProcesar(0);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            setaProcesar(0);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(cantidad);
    }

    public int getaProcesar() {
        return aProcesar;
    }

    public void setaProcesar(int aProcesar) {
        this.aProcesar = aProcesar;
    }
}
