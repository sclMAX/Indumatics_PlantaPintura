package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.OrdenPintura;
import indumatics_plantapintura.data.clases.OrdenPinturaDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrdenPinturaDP {

    private static String sql;
    private static Connection conn = AccessConector.getInstance();

    public static int add(OrdenPintura data) throws SQLException {
        int res = -1;
        sql = "INSERT INTO OPINTURA (IDPROVEEDOR, FECHA, FECHAENTREGA, COMENTARIOS, PROCESADO) "
                + "VALUES(?, ?, ?, ?, ?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, data.getIdproveedor());
        ps.setDate(2, data.getFecha());
        ps.setDate(3, data.getFechaentrega());
        if (data.getComentarios() != null) {
            ps.setString(4, data.getComentarios());
        } else {
            ps.setString(4, " ");
        }
        ps.setBoolean(5, data.isProcesado());
        ps.executeUpdate();
        res = AccessConector.getPrimaryKey();
        if (data.getDetalles() != null) {
            for (OrdenPinturaDetalle detalle : data.getDetalles()) {
                detalle.setIdOrden(res);
                if (detalle.getaProcesar() > 0) {
                    int id_detalle = OrdenPinturaDetalleDP.add(detalle);
                    if (id_detalle >= 0) {
                        detalle.setId(id_detalle);
                    }
                }
            }
        }
        return res;
    }

    public static Set<OrdenPintura> getAll() throws SQLException {
        Set<OrdenPintura> res = new HashSet<>();
        sql = "SELECT * FROM OPINTURA;";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    public static OrdenPintura getOne(int nro) throws SQLException {
        OrdenPintura res = null;
        sql = "SELECT * "
                + "FROM OPINTURA "
                + "WHERE OPINTURA.NRO = " + Integer.toString(nro);
        ResultSet rs = ComunDP.getData(sql);
        if (rs != null && rs.next()) {
            res = DbToObj(rs);
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
