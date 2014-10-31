package indumatics_plantapintura.data.clases;

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
    private int cantidad;
    private int aProcesar;
    private String idPerfil;
    private int colorO;
    private int colorD;
    private String comentarios;
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

    private static final int dfv = -1000;

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
            this.perfil = PerfilDP.getOne(idPerfil);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            this.perfil = null;
        }
    }

    public int getColorO() {
        return colorO;
    }

    public void setColorO(int colorH) {
        this.colorO = colorH;
        try {
            this.colorOrigen = ColorDP.getOne(colorH);
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

    public Color getColorOrigen() {
        return colorOrigen;
    }

    public void setColorOrigen(Color colorOrigen) {
        this.colorOrigen = colorOrigen;
        this.colorO = colorOrigen.getId();
    }

    public Color getColorDestino() {
        return colorDestino;
    }

    public void setColorDestino(Color colorDestino) {
        this.colorDestino = colorDestino;
        this.colorD = colorDestino.getId();
    }

    public int getStockTotal() {
        try {
            if (stockTotal != dfv) {
                return stockTotal;
            }
            return StockDP.getStockTotalPerfil(perfil);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getStockDisponible() {
        try {
            if (stockDisponible != dfv) {
                return stockDisponible;
            }
            return StockDP.getStockDisponiblePerfil(perfil);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getStockColorOrigen() {
        try {
            if (stockColorOrigen != dfv) {
                return stockColorOrigen;
            }
            return StockDP.getStockPerfilColor(perfil, colorOrigen);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getStockColorDestino() {
        try {
            if (stockColorDestino != dfv) {
                return stockColorDestino;
            }
            return StockDP.getStockPerfilColor(perfil, colorDestino);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getEnProcesoColorOrigen() {
        try {
            if (enProcesoColorOrigen != dfv) {
                return enProcesoColorOrigen;
            }
            return OrdenPinturaDetalleDP.getProcesoOrigen(idPerfil, colorO);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getEnProcesoColorDestino() {
        try {
            if (enProcesoColorDestino != dfv) {
                return enProcesoColorDestino;
            }
            return OrdenPinturaDetalleDP.getProcesoDestino(idPerfil, colorD);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getStockNatural() {
        try {
            if (stockNatural != dfv) {
                return stockNatural;
            }
            return StockDP.getOne(perfil, new Color(ColorDP.ID_NATURAL)).getStock();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getPedidosNatural() {
        if (pedidosNatural != dfv) {
            return pedidosNatural;
        }
        try {
            return StockDP.getPedidosNatural(perfil);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenPinturaDetalle.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public void validarCantidad() {
        try {
            int c = 0;
            c = (getCantidad() + StockDP.getOne(perfil, colorDestino).getMpp())
                    - (getStockColorDestino() + getEnProcesoColorDestino());
            if (c >= 0) {
                setaProcesar(c);
            } else {
                setaProcesar(0);
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
