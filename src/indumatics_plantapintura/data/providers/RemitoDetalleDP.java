/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.Remito;
import indumatics_plantapintura.data.clases.RemitoDetalle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxi
 */
public class RemitoDetalleDP {

    final static Connection con = AccessConector.getInstance();

    public Set<RemitoDetalle> getAll() {
        Set<RemitoDetalle> res = new HashSet<>();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CLIENTES_DETALLE_REMITO ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                res.add(DbToObj(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
     public Set<RemitoDetalle> getAllRemito( Remito remito) {
        Set<RemitoDetalle> res = new HashSet<>();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM CLIENTES_DETALLE_REMITO "
                    + " WHERE CLIENTES_DETALLE_REMITO.IDREMITO = " + Integer.toString(remito.getIdremito());
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                res.add(DbToObj(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private RemitoDetalle DbToObj(ResultSet rs) throws SQLException {
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
