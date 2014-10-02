package indumatics_plantapintura.data;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class AccessConector {

    private static AccessConector instance = null;
    private static Connection con = null;
    private static final String DataBase = "K:\\INDUMATICS DATA\\INDUMATICS S.A\\GESTION\\VENTPERF_new.accdb";

    private AccessConector() {
        
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new AccessConector();
            conectar();
        }
    }

    private static void conectar() {
        try {
            String controlador = "sun.jdbc.odbc.JdbcOdbcDriver";
            Class.forName(controlador).newInstance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el Controlador");
        }
        try {
            String DSN = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + DataBase;
            String user = "";
            String password = "";
            con = DriverManager.getConnection(DSN, user, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la conexion " + e);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            createInstance();
        }
        return con;
    }
}
