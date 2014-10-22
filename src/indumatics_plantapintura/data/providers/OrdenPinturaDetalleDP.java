package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.OrdenPintura;
import indumatics_plantapintura.data.clases.OrdenPinturaDetalle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrdenPinturaDetalleDP {

    private static String sql;

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

    private static OrdenPinturaDetalle DbToObj(ResultSet rs) throws SQLException {
        OrdenPinturaDetalle res = new OrdenPinturaDetalle();
        res.setId(rs.getInt("ID"));
        res.setIdOrden(rs.getInt("IDORDEN"));
        res.setCantidad(rs.getInt("CANTIDAD"));
        res.setIdPerfil(rs.getString("IDPERFIL"));
        res.setColorH(rs.getInt("COLORH"));
        res.setColorD(rs.getInt("COLORD"));
        res.setComentarios(rs.getString("COMENTARIOS"));
        res.setProc(rs.getBoolean("PROC"));
        res.setPintado(rs.getBoolean("PINTADO"));
        res.setStk_Act(rs.getBoolean("STK_ACT"));
        res.setAct_Stk(rs.getBoolean("ACT_STK"));
        res.setLargo(rs.getInt("LARGO"));
        return res;
    }
}
