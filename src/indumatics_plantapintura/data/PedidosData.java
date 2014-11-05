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

public class PedidosData {

    private static String sql;

    public static Set<Color> getColoresPedidos() {
        Set<Color> res = new HashSet<>();
        try {
            sql = "SELECT DISTINCT (CLIENTES_DETALLE_REMITO.COLOR)"
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO "
                    + "ON CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or "
                    + "(CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False));";
            try (ResultSet rs = ComunDP.getData(sql)) {
                if (rs != null) {
                    while (rs.next()) {
                        Color color = ColorDP.getOne(rs.getInt("COLOR"));
                        if (color != null) {
                            res.add(color);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error...", "Error al buscar colores pedidos.\nERROR: "
                    + ex.getMessage() + "\nSQL State: " + ex.getSQLState());
        }
        return res;
    }

    public static Set<Color> getColoresPedidosCliente(Set<Cliente> clientes) {
        Set<Color> res;
        Set<Cliente> subclientes = new HashSet<>();
        res = new HashSet<>();
        try {
            sql = "SELECT DISTINCT (CLIENTES_DETALLE_REMITO.COLOR)"
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO "
                    + "ON CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or "
                    + "(CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False)"
                    + "AND ({CLIENTES}));";
            String subSql = "";
            for (Cliente cliente : clientes) {
                subSql = subSql + Integer.toString(cliente.getIdcliente()) + ", ";
            }
            subSql = subSql.substring(0, subSql.length() - 2);
            
            subSql = "CLIENTES_REMITOS.IDCLIENTE IN (" + subSql+")";
            sql = sql.replace("{CLIENTES}", subSql);
            try (ResultSet rs = ComunDP.getData(sql)) {
                if (rs != null) {
                    while (rs.next()) {
                        Color color = ColorDP.getOne(rs.getInt("COLOR"));
                        if (color != null) {
                            res.add(color);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error...", "Error al buscar colores pedidos por cliente.\nERROR: "
                    + ex.getMessage() + "\nSQL State: " + ex.getSQLState());
        }
        return res;
    }

    public static Set<Cliente> getClientesPedidos() {
        Set<Cliente> l = new HashSet<>();
        try {
            sql = "SELECT DISTINCT (CLIENTES_REMITOS.IDCLIENTE) AS IDCLIENTE "
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                    + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False));";
            try (ResultSet rs = ComunDP.getData(sql)) {
                if (rs != null) {
                    while (rs.next()) {
                        Cliente cliente = ClienteDP.getOne(rs.getInt("IDCLIENTE"));
                        if (cliente != null) {
                            l.add(cliente);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error...", "Error al buscar clientes con pedidos.\nERROR: "
                    + ex.getMessage() + "\nSQL State: " + ex.getSQLState());
        }
        return l;
    }

    public static Set<Cliente> getClientesPedidosColor(Color color) {
        Set<Cliente> res = new HashSet<>();
        try {
            sql = "SELECT DISTINCT (CLIENTES_REMITOS.IDCLIENTE) AS IDCLIENTE "
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                    + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False) AND (CLIENTES_DETALLE_REMITO.COLOR = "
                    + Integer.toString(color.getId()) + "));";
            try (ResultSet rs = ComunDP.getData(sql)) {
                if (rs != null) {
                    while (rs.next()) {
                        Cliente cliente = ClienteDP.getOne(rs.getInt("IDCLIENTE"));
                        if (cliente != null) {
                            res.add(cliente);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error...", "Error al buscar clientes con pedidos por color.\nERROR: "
                    + ex.getMessage() + "\nSQL State: " + ex.getSQLState());
        }
        return res;
    }

    public static Set<Cliente> getClientesPedidosColores(Set<Color> colores) {
        Set<Cliente> l = new HashSet<>();
        try {
            sql = "SELECT DISTINCT (CLIENTES_REMITOS.IDCLIENTE) AS IDCLIENTE "
                    + "FROM CLIENTES_REMITOS INNER JOIN CLIENTES_DETALLE_REMITO ON "
                    + "CLIENTES_REMITOS.IDREMITO = CLIENTES_DETALLE_REMITO.IDREMITO "
                    + "WHERE (((CLIENTES_REMITOS.TIPODOC)=1 Or (CLIENTES_REMITOS.TIPODOC)=4) AND "
                    + "((CLIENTES_REMITOS.ENTREGADO)=False) AND "
                    + "((CLIENTES_DETALLE_REMITO.ACT_STOCK)=False) AND ( ";
            String tmp = "";
            for (Color color : colores) {
                tmp = tmp + Integer.toString(color.getId()) + ", ";
            }
            tmp = tmp.substring(0, tmp.length() - 2);

            sql = sql + " CLIENTES_DETALLE_REMITO.COLOR IN (" + tmp + ")";
            sql = sql + "));";
            try (ResultSet rs = ComunDP.getData(sql)) {
                if (rs != null) {
                    while (rs.next()) {
                        Cliente cliente = ClienteDP.getOne(rs.getInt("IDCLIENTE"));
                        if (cliente != null) {
                            l.add(cliente);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error...", "Error al buscar clientes con pedidos por colores.\nERROR: "
                    + ex.getMessage() + "\nSQL State: " + ex.getSQLState());
        }
        return l;
    }

    public static Set<Remito> getPedidosCC(Set<Color> colores, Set<Cliente> clientes) {
        Set<Remito> res = new HashSet<>();
        Set<Cliente> subclientes = new HashSet<>();
        try {
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
            String tmp = "";
            for (Cliente cliente : clientes) {
                tmp = tmp + Integer.toString(cliente.getIdcliente()) + ", ";
            }
            tmp = tmp.substring(0, tmp.length() - 2);
            sql = sql + "CLIENTES_REMITOS.IDCLIENTE IN (" + tmp + ")";
            sql = sql + "));";
            try (ResultSet rs = ComunDP.getData(sql)) {
                if (rs != null) {
                    while (rs.next()) {
                        Remito pedido = RemitoDP.getOne(rs.getInt("PEDIDO"));
                        if (pedido != null) {
                            res.add(pedido);
                        }
                    }
                }
            }
            if (!subclientes.isEmpty()) {
                for (Remito rem : getPedidosCC(colores, subclientes)) {
                    res.add(rem);
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error...", "Error al buscar pedidos por cliente y color.\nERROR: "
                    + ex.getMessage() + "\nSQL State: " + ex.getSQLState());
        }
        return res;
    }

    public static Set<OrdenPinturaDetalle> genOrdenPedidosPorColor(Set<Remito> pedidos, Color color) {
        Set<OrdenPinturaDetalle> orden = new HashSet<>();
        try {
            sql = "SELECT CLIENTES_DETALLE_REMITO.CANTIDAD, "
                    + "CLIENTES_DETALLE_REMITO.IDPERFIL, CLIENTES_DETALLE_REMITO.LARGO "
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
                String tmp = Integer.toString(pedido.getIdremito()) + ", ";
                subSql = subSql + tmp;
            }
            subSql = subSql.substring(0, subSql.length() - 2);
            subSql = "CLIENTES_DETALLE_REMITO.IDREMITO IN (" + subSql + ") ";
            sql = sql.replace("{PEDIDOS}", subSql);
            sql = "SELECT Sum(T.CANTIDAD) AS CANTIDAD, T.IDPERFIL, T.LARGO "
                    + "FROM (" + sql + ") AS T "
                    + "GROUP BY T.IDPERFIL, T.LARGO "
                    + "ORDER BY T.IDPERFIL; ";
            Color pretratado = ColorDP.getOne(ColorDP.ID_PRETRATADO);
            try (ResultSet rs = ComunDP.getData(sql)) {
                if (rs != null) {
                    while (rs.next()) {
                        OrdenPinturaDetalle detalle = new OrdenPinturaDetalle();
                        detalle.setCantidad(rs.getInt("CANTIDAD"));
                        detalle.setIdPerfil(rs.getString("IDPERFIL"));
                        detalle.setLargo(rs.getInt("LARGO"));
                        detalle.setColorOrigen(pretratado);
                        detalle.setColorDestino(color);
                        orden.add(detalle);
                    }
                }
            }
        } catch (SQLException ex) {
            Utils.showError("Error...", "Error al generar orden.\nERROR: "
                    + ex.getMessage() + "\nSQL State: " + ex.getSQLState());
        }
        return orden;
    }
}
