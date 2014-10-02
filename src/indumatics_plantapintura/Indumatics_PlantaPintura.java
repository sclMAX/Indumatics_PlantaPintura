package indumatics_plantapintura;

import indumatics_plantapintura.data.clases.Color;
import indumatics_plantapintura.data.PedidosData;
import indumatics_plantapintura.data.clases.Cliente;
import java.util.Set;


public class Indumatics_PlantaPintura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

//        Connection data = AccessConector.getInstance();
//        Statement st = data.createStatement();
//
//        ResultSet rs = st.executeQuery("SELECT * FROM CLIENTES_DETALLE_REMITO");
//        int x = 0;
//        ResultSetMetaData md = rs.getMetaData();
//        int col = md.getColumnCount();
//        while (rs.next()) {
//            String res = md.getColumnName(1) + ":" + rs.getString(1);
//
//            for (int i = 2; i < col; i++) {
//                res = res + " | " + md.getColumnName(i) + ":" + rs.getString(i);
//            }
//            System.out.println(res);
//            x++;
//        }
//
//        System.out.println("Registros encontrados =" + x);
        Set<Cliente> l = PedidosData.getClientesPediso();
        for (Cliente color : l) {
            System.out.println(color);
            
            
        }
    }

}
