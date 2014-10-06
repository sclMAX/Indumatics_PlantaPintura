package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.Perfil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerfilDP {

    final static Connection con = AccessConector.getInstance();

    public Set<Perfil> getAll() {
        Set<Perfil> res = new HashSet<>();
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM PERFILES ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                res.add(DbToObj(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public Perfil getOne(String id){
        Perfil res = null;
        try {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM PERFILES WHERE PERFILES.IDPERF =  '" + id+"'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                res = DbToObj(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PerfilDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    private Perfil DbToObj(ResultSet rs) throws SQLException {
        Perfil res = new Perfil();
        res.setIdperf(rs.getString("IDPERF"));
        res.setCodproveedor(rs.getString("CODPROVEEDOR"));
        res.setDescripcion(rs.getString("DESCRIPCION"));
        res.setLargo(rs.getInt("LARGO"));
        res.setPxm(rs.getInt("PXM"));
        res.setBxp(rs.getInt("BxP"));
        res.setImage(rs.getObject("IMAGE"));
        res.setIdproveedor(rs.getInt("IDPROVEEDOR"));
        res.setAdicional(rs.getDouble("ADICIONAL"));
        res.setPreciocompra(rs.getDouble("PRECIOCOMPRA"));
        res.setUtilidad(rs.getDouble("UTILIDAD"));
        res.setPrecioventa(rs.getDouble("PRECIOVENTA"));
        res.setDisponible(rs.getBoolean("DISPONIBLE"));
        res.setIdlinea(rs.getInt("IDLINEA"));
        res.setSel(rs.getBoolean("SEL"));
        res.setAux1(rs.getString("AXU1"));
        res.setAux2(rs.getString("AUX2"));
        res.setFua(rs.getDate("FUA"));
        res.setAct_stock(rs.getBoolean("ACT_STOCK"));
        res.setMpp(rs.getInt("MPP"));
        res.setIdlista(rs.getInt("IDLISTA"));
        res.setRubro(rs.getInt("RUBRO"));
        return res;
    }
}
