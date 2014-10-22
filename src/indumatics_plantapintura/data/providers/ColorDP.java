package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorDP {

    private static String sql;

    public static Color getOne(int id) throws SQLException {
        Color res = null;
        sql = "SELECT * FROM Colores "
                + "WHERE Colores.id = " + Integer.toString(id) + " ;";
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            res = DbToObj(rs);
        }
        return res;
    }

    private static Color DbToObj(ResultSet rs) throws SQLException {
        Color res = new Color();
        res.setId(rs.getInt("ID"));
        res.setColor(rs.getString("COLOR"));
        res.setPrecio(rs.getDouble("PRECIO"));
        res.setIncremento(rs.getDouble("INCREMENTO"));
        res.setComentarios(rs.getString("COMENTARIOS"));
        res.setFua(rs.getDate("FUA"));
        res.setEspintura(rs.getBoolean("ESPINTURA"));
        res.setIdplanta(rs.getInt("IDPLANTA"));
        return res;
    }

}
