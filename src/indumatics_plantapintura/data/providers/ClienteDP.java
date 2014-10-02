package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDP {

    final static Connection con = AccessConector.getInstance();

    public Cliente getOne(int id) {
        Cliente res = null;
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM Clientes WHERE Clientes.idcliente = " + Integer.toString(id);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                res = new Cliente();
                res.setIdcliente(rs.getInt("IDCLIENTE"));
                res.setRazon_social(rs.getString("RAZON_SOCIAL"));
                res.setDireccion(rs.getString("DIRECCION"));
                res.setTelefonos(rs.getString("TELEFONOS"));
                res.setCp(rs.getString("CP"));
                res.setLocalidad(rs.getString("LOCALIDAD"));
                res.setProvincia(rs.getString("PROVINCIA"));
                res.setPais(rs.getString("PAIS"));
                res.setFac_nombre(rs.getString("FAC_NOMBRE"));
                res.setFac_direccion(rs.getString("FAC_DIRECCION"));
                res.setFac_cuit(rs.getString("FAC_CUIT"));
                res.setIdtransporte(rs.getInt("IDTRANSPORTE"));
                res.setWeb(rs.getString("WEB"));
                res.setMail(rs.getString("MAIL"));
                res.setComentarios(rs.getString("COMENTARIOS"));
                res.setPor_fac(rs.getDouble("POR_FAC"));
                res.setIva(rs.getDouble("IVA"));
                res.setDescuento(rs.getDouble("DESCUENTO"));
                res.setColor(rs.getInt("COLOR"));
                res.setPrecio_natural(rs.getDouble("PRECIO_NATURAL"));
                res.setPrecio_blanco(rs.getDouble("PRECIO_BLANCO"));
                res.setPrecio_color(rs.getDouble("PRECIO_COLOR"));
                res.setSel(rs.getBoolean("SEL"));
                res.setFua(rs.getDate("FUA"));
                res.setFi(rs.getDate("FI"));
                res.setRuta(rs.getInt("RUTA"));
                res.setAux1(rs.getString("AUX1"));
                res.setAux2(rs.getString("AUX2"));
                res.setDes_natural(rs.getDouble("DES_NATURAL"));
                res.setDes_blanco(rs.getDouble("DES_BLANCO"));
                res.setDes_color(rs.getDouble("DES_COLOR"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

}
