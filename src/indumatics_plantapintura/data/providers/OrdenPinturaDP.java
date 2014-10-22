package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.OrdenPintura;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrdenPinturaDP {

    private static String sql;

    public static Set<OrdenPintura> getAll() throws SQLException {
        Set<OrdenPintura> res = new HashSet<>();
        sql = "SELECT * FROM OPINTURA;";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    private static OrdenPintura DbToObj(ResultSet rs) throws SQLException {
        OrdenPintura res = new OrdenPintura();
        res.setNro(rs.getInt("NRO"));
        res.setIdproveedor(rs.getInt("IDPROVEEDOR"));
        res.setFecha(rs.getDate("FECHA"));
        res.setFechaentrega(rs.getDate("FECHAENTREGA"));
        res.setComentarios(rs.getString("COMENTARIOS"));
        res.setProcesado(rs.getBoolean("PROCESADO"));
        return res;
    }
}
