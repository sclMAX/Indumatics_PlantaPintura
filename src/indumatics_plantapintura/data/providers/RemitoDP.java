package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.Remito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RemitoDP {

    private static String sql;

    public static Remito getOne(int id) throws SQLException {
        Remito res = null;
        sql = "SELECT * FROM CLIENTES_REMITOS "
                + "WHERE CLIENTES_REMITOS.idremito = " + Integer.toString(id);
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            res = DbToObj(rs);
        }
        return res;
    }

    public static Set<Remito> getAll() throws SQLException {
        Set<Remito> res = new HashSet<>();
        sql = "SELECT * FROM CLIENTES_REMITOS ";
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            res.add(DbToObj(rs));
        }
        return res;
    }

    private static Remito DbToObj(ResultSet rs) throws SQLException {
        Remito res = new Remito();
        res.setIdremito(rs.getInt("IDREMITO"));
        res.setIdcliente(rs.getInt("IDCLIENTE"));
        res.setTipodoc(rs.getInt("TIPODOC"));
        res.setFecha(rs.getDate("FECHA"));
        res.setFechaentrega(rs.getDate("FECHAENTREGA"));
        res.setTransporte(rs.getInt("TRANPORTE"));
        res.setComentarios(rs.getString("COMENTARIOS"));
        res.setChequeado(rs.getBoolean("CHEQUEADO"));
        res.setPrecesado(rs.getBoolean("PRECESADO"));
        res.setEntregado(rs.getBoolean("ENTREGADO"));
        res.setStockpro(rs.getBoolean("STOCKPRO"));
        res.setSel(rs.getBoolean("SEL"));
        res.setAux1(rs.getString("AUX1"));
        res.setAux2(rs.getString("AUX2"));
        res.setPagado(rs.getBoolean("PAGADO"));
        res.setCant_pack(rs.getInt("CANT_PACK"));
        res.setPeso_pack(rs.getDouble("PESO_PACK"));
        return res;

    }
}
