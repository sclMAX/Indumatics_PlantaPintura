package indumatics_plantapintura.data.providers;

import indumatics_plantapintura.data.AccessConector;
import indumatics_plantapintura.data.clases.Cliente;
import java.sql.Connection;

public class ClienteDP {

    final static Connection con = AccessConector.getInstance();

    public Cliente getOne() {
        Cliente res = null;

        return res;
    }

}
