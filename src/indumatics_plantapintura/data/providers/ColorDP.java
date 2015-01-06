package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ColorDP {

    private static String sql;
    public static final int ID_NATURAL = 5;
    public static final int ID_PRETRATADO = 35;
    public static final int ID_BLANCO = 2;

    public static Color getOne(int id) throws SQLException {
        Color res = null;
        sql = "SELECT * FROM Colores "
                + "WHERE Colores.id = " + Integer.toString(id) + " ;";
        try (ResultSet rs = ComunDP.getData(sql)) {
            if (rs != null) {
                if (rs.next()) {
                    res = DbToObj(rs);
                }
            }
        }
        return res;
    }

    public static Set<Color> getAll() throws SQLException {
        Set<Color> res = new HashSet<>();
        sql = "SELECT * FROM COLORES;";
        try (ResultSet rs = ComunDP.getData(sql)) {
            while (rs.next()) {
                res.add(DbToObj(rs));
            }
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
