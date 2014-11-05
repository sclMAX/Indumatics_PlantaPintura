package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.Ruta;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RutaDP {

    private static String sql;

    public static Ruta getOne(int id) throws SQLException {
        Ruta res = null;
        sql = "SELECT * FROM RUTAS WHERE RUTAS.ID = " + Integer.toString(id);
        try (ResultSet rs = ComunDP.getData(sql)) {
            if (rs.next()) {
                res = DbToObj(rs);
            }
        }
        return res;
    }

    private static Ruta DbToObj(ResultSet rs) throws SQLException {
        Ruta r = new Ruta();
        r.setId(rs.getInt("ID"));
        r.setRuta(rs.getString("RUTA"));
        r.setDescripcion(rs.getString("DESCRIPCION"));
        r.setDias(rs.getString("DIAS"));
        r.setIncremento(rs.getDouble("INCREMENTO"));
        return r;
    }
}
