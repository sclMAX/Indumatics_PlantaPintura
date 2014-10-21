package indumatics_plantapintura.data;

import indumatics_plantapintura.data.clases.Cliente;
import indumatics_plantapintura.data.providers.ColorDP;
import indumatics_plantapintura.data.clases.Color;
import indumatics_plantapintura.data.clases.OrdenPinturaDetalle;
import indumatics_plantapintura.data.clases.Remito;
import indumatics_plantapintura.data.providers.ClienteDP;
import indumatics_plantapintura.data.providers.ComunDP;
import indumatics_plantapintura.data.providers.RemitoDP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidosData {

    public static Set<Color> getColoresPedidos() {
        Set<Color> l;
        l = new HashSet<>();
        try {
            String sql;
            sql = "SELECT DISTINCT (CLIENTES_DETALLE_REMITO.COLOR)"
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO "
                    + "ON CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or "
                    + "(CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False));";
            ResultSet rs = ComunDP.getData(sql);
            while (rs.next()) {
                Color color = ColorDP.getOne(rs.getInt("COLOR"));
                if (color != null) {
                    l.add(color);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public static Set<Cliente> getClientesPedidos() {
        Set<Cliente> l = new HashSet<>();
        try {
            String sql;
            sql = "SELECT DISTINCT (CLIENTES_REMITOS.IDCLIENTE) AS IDCLIENTE "
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                    + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False));";
            ResultSet rs = ComunDP.getData(sql);
            while (rs.next()) {
                Cliente cliente = ClienteDP.getOne(rs.getInt("IDCLIENTE"));
                if (cliente != null) {
                    l.add(cliente);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public static Set<Cliente> getClientesPedidosColor(Color color) {
        Set<Cliente> l = new HashSet<>();
        try {
            String sql;
            sql = "SELECT DISTINCT (CLIENTES_REMITOS.IDCLIENTE) AS IDCLIENTE "
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                    + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False) AND (CLIENTES_DETALLE_REMITO.COLOR = "
                    + Integer.toString(color.getId()) + "));";
            ResultSet rs = ComunDP.getData(sql);
            while (rs.next()) {
                Cliente cliente = ClienteDP.getOne(rs.getInt("IDCLIENTE"));
                if (cliente != null) {
                    l.add(cliente);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public static Set<Cliente> getClientesPedidosColores(Set<Color> colores) {
        Set<Cliente> l = new HashSet<>();
        try {
            String sql;
            sql = "SELECT DISTINCT (CLIENTES_REMITOS.IDCLIENTE) AS IDCLIENTE "
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                    + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False) AND ( ";
            for (Color color : colores) {
                sql = sql + "(CLIENTES_DETALLE_REMITO.COLOR = "
                        + Integer.toString(color.getId()) + ") OR ";
            }
            sql = sql.substring(0, sql.length() - 3);
            sql = sql + "));";
            ResultSet rs = ComunDP.getData(sql);
            while (rs.next()) {
                Cliente cliente = ClienteDP.getOne(rs.getInt("IDCLIENTE"));
                if (cliente != null) {
                    l.add(cliente);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public static Set<Remito> getPedidosCC(Set<Color> colores, Set<Cliente> clientes) {
        Set<Remito> res = new HashSet<>();
        try {
            String sql;
            sql = "SELECT DISTINCT (CLIENTES_REMITOS.IDREMITO) AS PEDIDO "
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                    + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False) AND ( ";
            for (Color color : colores) {
                sql = sql + "(CLIENTES_DETALLE_REMITO.COLOR = "
                        + Integer.toString(color.getId()) + ") OR ";
            }
            sql = sql.substring(0, sql.length() - 3);
            sql = sql + ") AND( ";
            for (Cliente cliente : clientes) {
                sql = sql + "(CLIENTES_REMITOS.IDCLIENTE = "
                        + Integer.toString(cliente.getIdcliente()) + ") OR ";
            }
            sql = sql.substring(0, sql.length() - 3);
            sql = sql + "));";
            ResultSet rs = ComunDP.getData(sql);
            while (rs.next()) {
                Remito pedido = RemitoDP.getOne(rs.getInt("PEDIDO"));
                if (pedido != null) {
                    res.add(pedido);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public static Set<OrdenPinturaDetalle> genOrdenPedidosPorColor(Set<Remito> pedidos, Color color) throws SQLException {
        Set<OrdenPinturaDetalle> orden = new HashSet<>();
        String sql = "SELECT CLIENTES_DETALLE_REMITO.CANTIDAD, "
                + "CLIENTES_DETALLE_REMITO.IDPERFIL, CLIENTES_DETALLE_REMITO.LARGO, "
                + "CLIENTES_DETALLE_REMITO.COLOR, CLIENTES_DETALLE_REMITO.PROCESADO, "
                + "CLIENTES_DETALLE_REMITO.EMBALADO, CLIENTES_DETALLE_REMITO.ACT_STOCK, "
                + "CLIENTES_REMITOS.ENTREGADO "
                + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                + "GROUP BY CLIENTES_DETALLE_REMITO.CANTIDAD,CLIENTES_DETALLE_REMITO.IDPERFIL, CLIENTES_DETALLE_REMITO.LARGO, "
                + "CLIENTES_DETALLE_REMITO.COLOR, CLIENTES_DETALLE_REMITO.PROCESADO, "
                + "CLIENTES_DETALLE_REMITO.EMBALADO, CLIENTES_DETALLE_REMITO.ACT_STOCK, "
                + "CLIENTES_REMITOS.ENTREGADO, CLIENTES_DETALLE_REMITO.IDREMITO "
                + "HAVING (((CLIENTES_DETALLE_REMITO.COLOR)= {COLOR}) AND "
                + "((CLIENTES_DETALLE_REMITO.PROCESADO)=False) AND "
                + "((CLIENTES_DETALLE_REMITO.EMBALADO)=False) AND "
                + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False) AND "
                + "((CLIENTES_REMITOS.ENTREGADO)=False)AND ({PEDIDOS}))";
        sql = sql.replace("{COLOR}", Integer.toString(color.getId()));
        String subSql = "";
        for (Remito pedido : pedidos) {
            String tmp = "(CLIENTES_DETALLE_REMITO.IDREMITO = " + Integer.toString(pedido.getIdremito()) + ") OR ";
            subSql = subSql + tmp;
        }
        subSql = subSql.substring(0, subSql.length() - 3);
        sql = sql.replace("{PEDIDOS}", subSql);
        sql = "SELECT Sum(T.CANTIDAD) AS CANTIDAD, T.IDPERFIL, T.LARGO "
                + "FROM (" + sql + ") AS T "
                + "GROUP BY T.IDPERFIL, T.LARGO "
                + "ORDER BY T.IDPERFIL; ";
        Color natural = ColorDP.getOne(5);
        ResultSet rs = ComunDP.getData(sql);
        while (rs.next()) {
            OrdenPinturaDetalle detalle = new OrdenPinturaDetalle();
            detalle.setCantidad(rs.getInt("CANTIDAD"));
            detalle.setIdPerfil(rs.getString("IDPERFIL"));
            detalle.setColorHorigen(natural);
            detalle.setColorDestino(color);
            orden.add(detalle);
        }
        return orden;
    }

}
