package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.Remito;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemitoDP {

    final static Connection con = AccessConector.getInstance();

    public Remito getOne(int id) {
        Remito res = null;
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CLIENTES_REMITOS "
                    + "WHERE CLIENTES_REMITOS.idremito = " + Integer.toString(id);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                res = DbToObj(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public Set<Remito> getAll() {
        Set<Remito> res = new HashSet<>();        
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CLIENTES_REMITOS ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                res.add(DbToObj(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private Remito DbToObj(ResultSet rs) throws SQLException {
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
