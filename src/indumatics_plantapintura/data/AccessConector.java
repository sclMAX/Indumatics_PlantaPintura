package indumatics_plantapintura.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccessConector {

    private static AccessConector instance = null;
    private static Connection con = null;
    private static final String DataBase = "K:\\INDUMATICS DATA\\INDUMATICS S.A\\GESTION\\VENTPERF_new.accdb";
    private static final String DSN = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + DataBase;
    private static final String user = "";
    private static final String password = "";

    private AccessConector() {

    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new AccessConector();
            conectar();
        }
    }
    
    public static void reConectar(){
        if (instance == null) {
            instance = new AccessConector();
            conectar();
        }else{
            con = null;
            conectar();            
        }
    }

    private static void conectar() {
        try {
            String controlador = "sun.jdbc.odbc.JdbcOdbcDriver";
            Class.forName(controlador).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            Utils.showError("ERROR...", "Error al cargar el Controlador.\n ERROR:" + e.getMessage());
        }
        try {
            con = DriverManager.getConnection(DSN, user, password);
        } catch (Exception e) {
            Utils.showError("ERROR...", "Error al realizar la conexion.\n ERROR:" + e.getMessage());
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            createInstance();
        }
        return con;
    }

    public static int getPrimaryKey() throws SQLException {
        int pk = -1;
        if (instance != null) {
            String sql = "SELECT @@IDENTITY";
            try (Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    pk = rs.getInt(1);
                }
            }
        }
        return pk;
    }
}
