package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.Color;
import indumatics_plantapintura.data.clases.Perfil;
import indumatics_plantapintura.data.clases.Stock;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class StockDP {

    private static String sql = "";

    public static Set<Stock> getAll() throws SQLException {
        Set<Stock> res = new HashSet<>();
        sql = "SELECT * FROM STOCK;";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    public static Stock getOne(Perfil perfil, Color color) throws SQLException {
        Stock res = null;
        sql = "SELECT * FROM STOCK "
                + "WHERE (STOCK.IDPERFIL = '" + perfil.getIdperf() + "') AND ("
                + "STOCK.COLOR = " + Integer.toString(color.getId()) + ");";
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            res = DbToObj(rs);
        }
        return res;
    }

    public static Set<Stock> getAllPerfil(Perfil perfil) throws SQLException {
        Set<Stock> res = new HashSet<>();
        sql = "SELECT * FROM STOCK "
                + "WHERE (STOCK.IDPERFIL = '" + perfil.getIdperf() + "');";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    public static int getStockTotalPerfil(Perfil perfil) throws SQLException {
        int res = 0;
        sql = "SELECT SUM(STOCK.STOCK) AS TOTAL FROM STOCK "
                + "WHERE (STOCK.IDPERFIL = '" + perfil.getIdperf() + "');";
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            res = rs.getInt("TOTAL");
        }
        return res;
    }
    
    public static int getStockDisponiblePerfil(Perfil perfil) throws SQLException {
        int res = 0;
        sql = "SELECT SUM(DISPONIBLE) AS TOTAL FROM STOCK_DISPONIBLE "
                + "WHERE (STOCK_TOTALBARRAS.IDPERF = '" + perfil.getIdperf() + "');";
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            res = rs.getInt("TOTAL");
        }
        return res;
    }

    public static Set<Stock> getAllColor(Color color) throws SQLException {
        Set<Stock> res = new HashSet<>();
        sql = "SELECT * FROM STOCK "
                + "WHERE (STOCK.COLOR = '" + Integer.toString(color.getId()) + "');";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    private static Stock DbToObj(ResultSet rs) throws SQLException {
        Stock res = new Stock();
        res.setIdPerfil(rs.getString("IDPERFIL"));
        res.setColor(rs.getInt("COLOR"));
        res.setStock(rs.getInt("STOCK"));
        res.setFecha_UV(rs.getDate("FECHA_UV"));
        res.setFecha_UC(rs.getDate("FECHA_UC"));
        res.setComentarios(rs.getString("COMENTARIOS"));
        res.setMpp(rs.getInt("MPP"));
        res.setFua(rs.getDate("FUA"));
        res.setSel(rs.getBoolean("SEL"));
        res.setAux1(rs.getString("AUX1"));
        res.setAux2(rs.getString("AUX2"));
        return res;
    }
}
