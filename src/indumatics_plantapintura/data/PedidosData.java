package indumatics_plantapintura.data;

import indumatics_plantapintura.data.providers.ColorDP;
import indumatics_plantapintura.data.clases.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidosData {

    private static final Connection con = AccessConector.getInstance();
    private static final ColorDP colorProvider = new ColorDP();

    public static Set<Color> getColoresPedidos() {
        Set<Color> l;
        l = new HashSet<>();
        try {
            Statement st = con.createStatement();
            String sql;
            sql = "SELECT DISTINCT (CLIENTES_DETALLE_REMITO.COLOR)\n"
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO "
                    + "ON CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO\n"
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or "
                    + "(CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False));";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Color color = colorProvider.getOne(rs.getInt(1));
                if (color != null) {
                    l.add(color);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    
    
}
