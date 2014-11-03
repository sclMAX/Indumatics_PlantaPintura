package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.OrdenPintura;
import indumatics_plantapintura.data.clases.OrdenPinturaDetalle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrdenPinturaDetalleDP {

    private static String sql;

    public static int add(OrdenPinturaDetalle data) throws SQLException {
        int res = -1;
        sql = "INSERT INTO OPINTURA_DETALLE (IDORDEN, CANTIDAD, IDPERFIL, "
                + "COLORH, COLORD, COMENTARIOS, PROC, PINTADO, STK_ACT, ACT_STK, LARGO) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = AccessConector.getInstance().prepareStatement(sql);
        ps.setInt(1, data.getIdOrden());
        ps.setInt(2, data.getaProcesar());
        ps.setString(3, data.getIdPerfil());
        ps.setInt(4, data.getColorO());
        ps.setInt(5, data.getColorD());
        ps.setString(6, data.getComentarios());
        ps.setBoolean(7, data.isProc());
        ps.setBoolean(8, data.isPintado());
        ps.setBoolean(9, data.isStk_Act());
        ps.setBoolean(10, data.isAct_Stk());
        ps.setInt(11, data.getLargo());
        ps.executeUpdate();
        res = AccessConector.getPrimaryKey();
        return res;
    }

    public static Set<OrdenPinturaDetalle> getDetalleOrden(OrdenPintura orden) throws SQLException {
        Set<OrdenPinturaDetalle> res = new HashSet<>();
        sql = "SELECT * FROM OPINTURA_DETALLE "
                + "WHERE IDORDEN = " + Integer.toString(orden.getNro());
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    public static OrdenPinturaDetalle getOne(int id) throws SQLException {
        sql = "SELECT * "
                + "FROM OPINTURA_DETALLE "
                + "WHERE ID = " + Integer.toString(id);
        ResultSet rs = ComunDP.getData(sql);
        if (rs != null && rs.next()) {
            return DbToObj(rs);
        } else {
            return null;
        }
    }

    public static int getProcesoDestino(String idPerfil, int idColor) throws SQLException {
        sql = "SELECT SUM (CANTIDAD) AS EnProcesoDestino "
                + "FROM OPINTURA_DETALLE "
                + "WHERE ((STK_ACT = False) "
                + "AND (IDPERFIL = '{PERFIL}' ) "
                + "AND (COLORD = {COLOR}));";
        sql = sql.replace("{PERFIL}", idPerfil);
        sql = sql.replace("{COLOR}", Integer.toString(idColor));
        ResultSet rs = ComunDP.getData(sql);
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static int getProcesoOrigen(String idPerfil, int idColor) throws SQLException {
        sql = "SELECT SUM (CANTIDAD) AS EnProcesoOrigen "
                + "FROM OPINTURA_DETALLE "
                + "WHERE ((STK_ACT = False) "
                + "AND (IDPERFIL = '{PERFIL}' ) "
                + "AND (COLORH = {COLOR}));";
        sql = sql.replace("{PERFIL}", idPerfil);
        sql = sql.replace("{COLOR}", Integer.toString(idColor));
        ResultSet rs = ComunDP.getData(sql);
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public static Set<OrdenPinturaDetalle> getOrdenPretratamiento() throws SQLException {
        Set<OrdenPinturaDetalle> orden = new HashSet<>();
        sql = "SELECT DISTINCT T.IDPERFIL, Sum(T.CANTIDAD) AS SumaDeCANTIDAD, T.LARGO "
                + "FROM (SELECT *  FROM OPINTURA_DETALLE WHERE ((COLORH = "+
                Integer.toString(ColorDP.ID_PRETRATADO) +") AND (STK_ACT = false)))  AS T "
                + "GROUP BY T.IDPERFIL, T.LARGO, T.IDPERFIL "
                + "ORDER BY T.IDPERFIL;";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            OrdenPinturaDetalle detalle = new OrdenPinturaDetalle();
            detalle.setCantidad(rs.getInt(2));
            detalle.setIdPerfil(rs.getString(1));
            detalle.setLargo(rs.getInt(3));
            detalle.setColorOrigen(ColorDP.getOne(ColorDP.ID_NATURAL));
            detalle.setColorDestino(ColorDP.getOne(ColorDP.ID_PRETRATADO));
            orden.add(detalle);
        }
        return orden;
    }

    private static OrdenPinturaDetalle DbToObj(ResultSet rs) throws SQLException {
        OrdenPinturaDetalle res = new OrdenPinturaDetalle();
        res.setId(rs.getInt("ID"));
        res.setIdOrden(rs.getInt("IDORDEN"));
        res.setCantidad(rs.getInt("CANTIDAD"));
        res.setIdPerfil(rs.getString("IDPERFIL"));
        res.setColorO(rs.getInt("COLORH"));
        res.setColorD(rs.getInt("COLORD"));
        res.setComentarios(rs.getString("COMENTARIOS"));
        res.setProc(rs.getBoolean("PROC")); // COLOR ORIGEN ACTUALIZADO
        res.setPintado(rs.getBoolean("PINTADO"));
        res.setStk_Act(rs.getBoolean("STK_ACT")); // COLOR DESTINO ACTUALIZADO
        res.setAct_Stk(rs.getBoolean("ACT_STK"));
        res.setLargo(rs.getInt("LARGO"));
        return res;
    }
}
