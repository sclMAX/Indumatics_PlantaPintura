package indumatics_plantapintura;

import indumatics_plantapintura.data.clases.Color;
import indumatics_plantapintura.data.PedidosData;
import indumatics_plantapintura.data.clases.Cliente;
import indumatics_plantapintura.data.clases.Remito;
import indumatics_plantapintura.data.clases.RemitoDetalle;
import indumatics_plantapintura.data.providers.PerfilDP;
import indumatics_plantapintura.data.providers.RemitoDP;
import indumatics_plantapintura.data.providers.RemitoDetalleDP;
import java.util.Set;

public class Indumatics_PlantaPintura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

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
            Set<RemitoDetalle> detalles = new RemitoDetalleDP().getAllRemito(remito);
            for (RemitoDetalle detalle : detalles){
                System.out.println("\n\t\t" + detalle);
                 System.out.println("\n\t\t\t" + new PerfilDP().getOne(detalle.getIdperfil()));
                
            }

        }

    }

}
