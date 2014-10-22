package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.clases.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDP {

    private static String sql;

    public static Cliente getOne(int id) throws SQLException {
        Cliente res = null;
        sql = "SELECT * FROM Clientes WHERE Clientes.idcliente = " + Integer.toString(id);
        ResultSet rs = ComunDP.getData(sql);
        if (rs.next()) {
            res = DbToObj(rs);
        }
        return res;
    }

    private static Cliente DbToObj(ResultSet rs) throws SQLException {
        Cliente res = new Cliente();
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
        return res;
    }
}
