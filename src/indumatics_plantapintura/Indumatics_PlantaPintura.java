package indumatics_plantapintura;

import indumatics_plantapintura.data.clases.Color;
import indumatics_plantapintura.data.PedidosData;
import indumatics_plantapintura.data.clases.Cliente;
import indumatics_plantapintura.data.clases.Remito;
import indumatics_plantapintura.data.providers.RemitoDP;
import java.util.HashSet;
import java.util.Set;

public class Indumatics_PlantaPintura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

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
        Set<Color> colores = PedidosData.getColoresPedidos();
        for (Color color : colores) {
            System.out.println("\n" + color);
            Set<Cliente> l = PedidosData.getClientesPedidosColor(color);
            for (Cliente cliente : l) {
                System.out.println("\n\t" + cliente);

            }

        }
        Color color;
        color = (Color) colores.toArray()[2];
       colores.clear();
       colores.add(color);
       for (Color c : colores) {
            System.out.println("\n" + c);
            Set<Cliente> l = PedidosData.getClientesPedidosColores(colores);
            for (Cliente cliente : l) {
                System.out.println("\n\t" + cliente);

            }
        }
        Set<Remito> remitos = new RemitoDP().getAll();
        for (Remito remito : remitos) {
            System.out.println("\n\t" + remito);

        }

    }

}
