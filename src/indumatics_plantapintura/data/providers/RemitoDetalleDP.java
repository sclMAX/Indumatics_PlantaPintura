package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.Remito;
import indumatics_plantapintura.data.clases.RemitoDetalle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RemitoDetalleDP {

    private static String sql;

    public static Set<RemitoDetalle> getAll() throws SQLException {
        Set<RemitoDetalle> res = new HashSet<>();
        sql = "SELECT * FROM CLIENTES_DETALLE_REMITO ";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    public static Set<RemitoDetalle> getAllRemito(Remito remito) throws SQLException {
        Set<RemitoDetalle> res = new HashSet<>();
        sql = "SELECT * FROM CLIENTES_DETALLE_REMITO "
                + " WHERE CLIENTES_DETALLE_REMITO.IDREMITO = " + Integer.toString(remito.getIdremito());
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    private static RemitoDetalle DbToObj(ResultSet rs) throws SQLException {
        RemitoDetalle res = new RemitoDetalle();
        res.setId(rs.getInt("ID"));
        res.setIdremito(rs.getInt("IDREMITO"));
        res.setCantidad(rs.getInt("CANTIDAD"));
        res.setIdperfil(rs.getString("IDPERFIL"));
        res.setLargo(rs.getInt("LARGO"));
        res.setColor(rs.getInt("COLOR"));
        res.setUnidades(rs.getDouble("UNIDADES"));
        res.setPrecio(rs.getDouble("PRECIO"));
        res.setDescuento(rs.getDouble("DESCUENTO"));
        res.setProcesado(rs.getBoolean("PROCESADO"));
        res.setRstock(rs.getBoolean("RSTOCK"));
        res.setEmbalado(rs.getBoolean("EMBALADO"));
        res.setAct_stock(rs.getBoolean("ACT_STOCK"));
        res.setComentarios(rs.getString("COMENTARIOS"));
        return res;
    }
}
