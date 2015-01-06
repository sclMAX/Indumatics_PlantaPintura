package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComunDP {

    private static Connection con = AccessConector.getInstance();

    /**
     * Ejecuta una consulta SQL a la base de datos
     *
     * @param sql String conteniendo la sentecia SQL
     * @return ResultSet COnteniendo los registros de la base de datos
     */
    public static ResultSet getData(String sql) {
        ResultSet rs;
        rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ComunDP.class.getName()).log(Level.SEVERE, null, ex);
            AccessConector.reConectar();
            con = AccessConector.getInstance();
            rs = getData(sql);
        }
        return rs;
    }
}
