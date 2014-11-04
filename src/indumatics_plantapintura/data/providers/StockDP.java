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
        sql = "SELECT SUM(STOCK.STOCK) AS TOTAL FROM STOCK "
                + "WHERE (STOCK.IDPERFIL = '" + perfil.getIdperf() + "');";
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            return rs.getInt("TOTAL");
        }
        return 0;
    }

    public static int getStockDisponiblePerfil(Perfil perfil) throws SQLException {
        sql = "SELECT SUM(DISPONIBLE) AS TOTAL FROM STOCK_DISPONIBLE "
                + "WHERE (STOCK_TOTALBARRAS.IDPERF = '" + perfil.getIdperf() + "');";
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            return rs.getInt("TOTAL");
        }
        return 0;
    }

    public static int getStockPerfilColor(Perfil perfil, Color color) throws SQLException {
        sql = "SELECT STOCK "
                + "FROM STOCK "
                + "WHERE (STOCK.IDPERFIL = '{PERFIL}')AND(STOCK.COLOR = {COLOR});";
        sql = sql.replace("{PERFIL}", perfil.getIdperf());
        sql = sql.replace("{COLOR}", Integer.toString(color.getId()));
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            return rs.getInt("STOCK");
        }
        return 0;
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

    public static int getPedidosNatural(Perfil perfil) throws SQLException {
        sql = "SELECT SUM(CANTIDAD) "
                + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False) AND "
                + "(COLOR = {COLOR}) AND (IDPERFIL = '{PERFIL}' ));";
        sql = sql.replace("{COLOR}", Integer.toString(ColorDP.ID_NATURAL));
        sql = sql.replace("{PERFIL}", perfil.getIdperf());
        ResultSet rs = ComunDP.getData(sql);
        if (rs != null && rs.next()) {
            return rs.getInt(1);
        }
        return 0;
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
