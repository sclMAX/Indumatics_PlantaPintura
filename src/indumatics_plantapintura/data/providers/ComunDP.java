package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComunDP {

    private final static Connection con = AccessConector.getInstance();

    /**
     * Ejecuta una consulta SQL a la base de datos
     *
     * @param sql String conteniendo la sentecia SQL
     * @return ResultSet COnteniendo los registros de la base de datos
     * @throws SQLException
     */
    public static ResultSet getData(String sql) throws SQLException {
        ResultSet rs;
        try (Statement st = con.createStatement()) {
            rs = st.executeQuery(sql);
        }
        return rs;
    }
}
