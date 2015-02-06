/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indumatics_plantapintura.guis;

import indumatics_plantapintura.data.frmEspera;
import indumatics_plantapintura.data.PedidosData;
import indumatics_plantapintura.data.clases.Cliente;
import indumatics_plantapintura.data.clases.Color;
import indumatics_plantapintura.data.clases.Remito;
import indumatics_plantapintura.data.clases.RemitoDetalle;
import indumatics_plantapintura.data.providers.OrdenPinturaDetalleDP;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maxi
 */
public class frmBuscarPedidos extends javax.swing.JFrame {

    private Set<Color> colores;
    private Set<Cliente> clientes;
    private Set<Remito> pedidos;
    private final ListSelectionModel lsmPedidos;
    frmListadoPerfilesPorColor jfOrden;

    public frmBuscarPedidos() {
        initComponents();

        lsmPedidos = jtPedidos.getSelectionModel();
        lsmPedidos.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int rowSelected = jtPedidos.getSelectedRow(), idColumn = 0;
                    if (rowSelected >= 0) {
                        for (int i = 0; i < jtPedidos.getColumnCount(); i++) {
                            if ("NRO".equals(jtPedidos.getColumnName(i))) {
                                idColumn = i;
                            }
                        }
                        Remito r = (Remito) jtPedidos.getValueAt(rowSelected, idColumn);
                        cargarTablaPedidoDetalle(r.getDetalles());
                    }
                }
            }
        });

    }

    private void cargarTablaColores(Set<Color> colores) {
        DefaultTableModel dtm = (DefaultTableModel) jtColores.getModel();
        dtm.setRowCount(0);
        if (colores != null) {
            for (Color color : colores) {
                Object[] obj = new Object[1];
                obj[0] = color;
                dtm.addRow(obj);
            }
        }
        jtColores.setModel(dtm);
        jpColores.setBorder(javax.swing.BorderFactory.createTitledBorder("COLORES "
                + "(" + Integer.toString(jtColores.getRowCount()) + ")"));
    }

    private void cargarTablaClientes(Set<Cliente> clientes) {
        DefaultTableModel dtm = (DefaultTableModel) jtClientes.getModel();
        dtm.setRowCount(0);
        if (clientes != null) {
            for (Cliente cliente : clientes) {
                Object[] obj = new Object[3];
                obj[0] = cliente;
                obj[1] = cliente.getLocalidad();
                obj[2] = cliente.getRutaObj();
                dtm.addRow(obj);
            }
        }
        jtClientes.setModel(dtm);
        jpClientes.setBorder(javax.swing.BorderFactory.createTitledBorder("CLIENTES "
                + "(" + Integer.toString(jtClientes.getRowCount()) + ")"));
    }

    private void cargarTablaPedidos(Set<Remito> pedidos) {
        DefaultTableModel dtm = (DefaultTableModel) jtPedidos.getModel();
        dtm.setRowCount(0);
        if (pedidos != null) {
            for (Remito pedido : pedidos) {
                Object[] obj = new Object[4];
                obj[0] = pedido;
                obj[1] = pedido.getFecha();
                obj[2] = pedido.getFechaentrega();
                obj[3] = pedido.getCliente();
                dtm.addRow(obj);
            }
        }
        jtPedidos.setModel(dtm);
        jpPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder("PEDIDOS "
                + "(" + Integer.toString(jtPedidos.getRowCount()) + ")"));
    }

    private void cargarTablaPedidoDetalle(Set<RemitoDetalle> detalles) {
        DefaultTableModel dtm = (DefaultTableModel) jtDetallePedido.getModel();
        dtm.setRowCount(0);
        if (detalles != null) {
            for (RemitoDetalle detalle : detalles) {
                Object[] obj = new Object[7];
                obj[0] = detalle;
                obj[1] = detalle.getPerfil();
                obj[2] = detalle.getColorObj();
                obj[3] = detalle.getLargo();
                obj[4] = detalle.isProcesado();
                obj[5] = detalle.isEmbalado();
                obj[6] = detalle.isAct_stock();
                dtm.addRow(obj);
            }
        }
        jtDetallePedido.setModel(dtm);
        jpDetallePedidos.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLE PEDIDO "
                + "(" + Integer.toString(jtDetallePedido.getRowCount()) + ")"));
    }

    private void buscarPedidos() {
        if ((colores != null) && (clientes != null)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    frmEspera espera = new frmEspera();
                    try {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        pedidos = PedidosData.getPedidosCC(colores, clientes);
                        cargarTablaPedidos(pedidos);
                    } finally {
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        espera.close();
                    }
                }
            }).start();
        }
    }

    private void buscarClientes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                frmEspera espera = null;
                try {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    if (colores != null) {
                        JOptionPane.showMessageDialog(null, "Se Buscaran solo clientes que "
                                + "tengan pedidos en los colores listados.\n"
                                + "Para buscar todos los qclientes que tengan "
                                + "pedidos BORRE la lista de colores primero.",
                                "Filtrado", JOptionPane.INFORMATION_MESSAGE);
                        espera = new frmEspera();
                        clientes = PedidosData.getClientesPedidosColores(colores);
                    } else {
                        espera = new frmEspera();
                        clientes = PedidosData.getClientesPedidos();
                    }
                    cargarTablaClientes(clientes);
                    pedidos = null;
                    cargarTablaPedidos(pedidos);
                } finally {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    if (espera != null) {
                        espera.close();
                    }
                }
            }
        }).start();
    }

    private void buscarColores() {
        if (clientes != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    frmEspera espera = new frmEspera();
                    try {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        colores = PedidosData.getColoresPedidosCliente(clientes);
                        cargarTablaColores(colores);
                        pedidos = null;
                        cargarTablaPedidos(pedidos);
                    } finally {
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        espera.close();
                    }
                }
            }).start();
        }
    }

    private void quitarPedidoSeleccionado() {
        if (jtPedidos.getSelectedRow() != -1) {
            pedidos.remove((Remito) jtPedidos.getValueAt(jtPedidos.getSelectedRow(), 0));
            cargarTablaPedidos(pedidos);
        }
    }

    private void quitarColorSeleccionado() {
        if (jtColores.getSelectedRowCount() > 0) {
            for (int i = 0; i < jtColores.getSelectedRowCount(); i++) {
                colores.remove((Color) jtColores.getValueAt(jtColores.getSelectedRows()[i], 0));
            }
            cargarTablaColores(colores);
            buscarClientes();
        }
    }

    private void quitarClienteSeleccionado() {
        if (jtClientes.getSelectedRowCount() > 0) {
            for (int i = 0; i < jtClientes.getSelectedRowCount(); i++) {
                clientes.remove((Cliente) jtClientes.getValueAt(jtClientes.getSelectedRows()[i], 0));
            }
            cargarTablaClientes(clientes);
            buscarColores();
        }
    }

    private void cargarListaPerfileColor() {
        if (jtColores.getSelectedRow() != -1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    frmEspera espera = new frmEspera();
                    try {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        Color c = (Color) jtColores.getValueAt(jtColores.getSelectedRow(), 0);
                        jfOrden = new frmListadoPerfilesPorColor(PedidosData.genOrdenPedidosPorColor(pedidos, c));
                        jfOrden.setTitle("Perfiles pedidos en " + c.getColor());
                        jfOrden.setVisible(true);

                    } finally {
                        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        espera.close();

                    }
                }
            }).start();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un color!",
                    "Listar Perfiles...", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cargarListaPerfilePretratar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                frmEspera espera = new frmEspera();
                try {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    try {
                        frmListadoPerfilesPorColor jfOrden;
                        jfOrden = new frmListadoPerfilesPorColor(OrdenPinturaDetalleDP.getOrdenPretratamiento());
                        jfOrden.setTitle("Perfiles a PRETRATAMIENTO");
                        jfOrden.setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(frmBuscarPedidos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } finally {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    espera.close();
                }
            }
        }).start();
    }

    private void cargarListaPintarStockBlanco() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                frmEspera espera = new frmEspera();
                try {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    frmListadoPerfilesPorColor jfOrden;
                    jfOrden = new frmListadoPerfilesPorColor(PedidosData.genOrdenStockBlanco());
                    jfOrden.setTitle("FALTANTES STOCK EN BLANCO");
                    jfOrden.setVisible(true);
                } finally {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    espera.close();
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpColores = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtColores = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jbColoresBuscar = new javax.swing.JButton();
        jbColoresQuitar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jbLimpiarLista = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jbVerPerfiles_x_Color = new javax.swing.JButton();
        jbPretratamiento = new javax.swing.JButton();
        jpClientes = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtClientes = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        jbClientesBuscar = new javax.swing.JButton();
        jbClientesQuitar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jbPintarStocKBlanco = new javax.swing.JButton();
        jpPedidos = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtPedidos = new javax.swing.JTable();
        jToolBar3 = new javax.swing.JToolBar();
        jtPedidosBuscar = new javax.swing.JButton();
        jtPedidosQuitar = new javax.swing.JButton();
        jpDetallePedidos = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtDetallePedido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INDUMATICS - Gestion Planta  V1.0 by MAX");
        setAutoRequestFocus(false);

        jpColores.setBorder(javax.swing.BorderFactory.createTitledBorder("COLORES"));

        jtColores.setAutoCreateRowSorter(true);
        jtColores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COLOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtColores.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(jtColores);
        jtColores.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        jbColoresBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_search_24x24.gif"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("indumatics_plantapintura/recursos/strings"); // NOI18N
        jbColoresBuscar.setToolTipText(bundle.getString("Buscar")); // NOI18N
        jbColoresBuscar.setFocusable(false);
        jbColoresBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbColoresBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbColoresBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbColoresBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbColoresBuscar);

        jbColoresQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_cancel_24x24.gif"))); // NOI18N
        jbColoresQuitar.setToolTipText(bundle.getString("Quitar")); // NOI18N
        jbColoresQuitar.setFocusable(false);
        jbColoresQuitar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbColoresQuitar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbColoresQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbColoresQuitarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbColoresQuitar);
        jToolBar1.add(jSeparator1);

        jbLimpiarLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_delete_24x24.gif"))); // NOI18N
        jbLimpiarLista.setToolTipText(bundle.getString("Quitar")); // NOI18N
        jbLimpiarLista.setFocusable(false);
        jbLimpiarLista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbLimpiarLista.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbLimpiarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimpiarListaActionPerformed(evt);
            }
        });
        jToolBar1.add(jbLimpiarLista);
        jToolBar1.add(jSeparator2);

        jbVerPerfiles_x_Color.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/options_24x24.gif"))); // NOI18N
        jbVerPerfiles_x_Color.setToolTipText(bundle.getString("listarperfilescolor")); // NOI18N
        jbVerPerfiles_x_Color.setFocusable(false);
        jbVerPerfiles_x_Color.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbVerPerfiles_x_Color.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbVerPerfiles_x_Color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVerPerfiles_x_ColorActionPerformed(evt);
            }
        });
        jToolBar1.add(jbVerPerfiles_x_Color);

        jbPretratamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/produccion_24x24.gif"))); // NOI18N
        jbPretratamiento.setToolTipText(bundle.getString("listarperfilescolor")); // NOI18N
        jbPretratamiento.setFocusable(false);
        jbPretratamiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbPretratamiento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbPretratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPretratamientoActionPerformed(evt);
            }
        });
        jToolBar1.add(jbPretratamiento);

        javax.swing.GroupLayout jpColoresLayout = new javax.swing.GroupLayout(jpColores);
        jpColores.setLayout(jpColoresLayout);
        jpColoresLayout.setHorizontalGroup(
            jpColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpColoresLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpColoresLayout.setVerticalGroup(
            jpColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );

        jpClientes.setBorder(javax.swing.BorderFactory.createTitledBorder("CLIENTES"));

        jtClientes.setAutoCreateRowSorter(true);
        jtClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLIENTE", "LOCALIDAD", "RUTA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtClientes.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(jtClientes);
        jtClientes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jToolBar2.setFloatable(false);
        jToolBar2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar2.setRollover(true);

        jbClientesBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_search_24x24.gif"))); // NOI18N
        jbClientesBuscar.setToolTipText(bundle.getString("Buscar")); // NOI18N
        jbClientesBuscar.setFocusable(false);
        jbClientesBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbClientesBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbClientesBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbClientesBuscarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbClientesBuscar);

        jbClientesQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_cancel_24x24.gif"))); // NOI18N
        jbClientesQuitar.setToolTipText(bundle.getString("Quitar")); // NOI18N
        jbClientesQuitar.setFocusable(false);
        jbClientesQuitar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbClientesQuitar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbClientesQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbClientesQuitarActionPerformed(evt);
            }
        });
        jToolBar2.add(jbClientesQuitar);
        jToolBar2.add(jSeparator3);

        jbPintarStocKBlanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/produccion_24x24.gif"))); // NOI18N
        jbPintarStocKBlanco.setToolTipText(bundle.getString("PerfilesStock")); // NOI18N
        jbPintarStocKBlanco.setFocusable(false);
        jbPintarStocKBlanco.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbPintarStocKBlanco.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbPintarStocKBlanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPintarStocKBlancoActionPerformed(evt);
            }
        });
        jToolBar2.add(jbPintarStocKBlanco);

        javax.swing.GroupLayout jpClientesLayout = new javax.swing.GroupLayout(jpClientes);
        jpClientes.setLayout(jpClientesLayout);
        jpClientesLayout.setHorizontalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClientesLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpClientesLayout.setVerticalGroup(
            jpClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jpPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder("PEDIDOS"));

        jtPedidos.setAutoCreateRowSorter(true);
        jtPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO", "Fecha", "Fecha Entrega", "Cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPedidos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtPedidos);
        jtPedidos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jToolBar3.setFloatable(false);
        jToolBar3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar3.setRollover(true);

        jtPedidosBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_search_24x24.gif"))); // NOI18N
        jtPedidosBuscar.setToolTipText(bundle.getString("Buscar")); // NOI18N
        jtPedidosBuscar.setFocusable(false);
        jtPedidosBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtPedidosBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtPedidosBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPedidosBuscarActionPerformed(evt);
            }
        });
        jToolBar3.add(jtPedidosBuscar);

        jtPedidosQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_cancel_24x24.gif"))); // NOI18N
        jtPedidosQuitar.setToolTipText(bundle.getString("Quitar")); // NOI18N
        jtPedidosQuitar.setFocusable(false);
        jtPedidosQuitar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jtPedidosQuitar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtPedidosQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPedidosQuitarActionPerformed(evt);
            }
        });
        jToolBar3.add(jtPedidosQuitar);

        javax.swing.GroupLayout jpPedidosLayout = new javax.swing.GroupLayout(jpPedidos);
        jpPedidos.setLayout(jpPedidosLayout);
        jpPedidosLayout.setHorizontalGroup(
            jpPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPedidosLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpPedidosLayout.setVerticalGroup(
            jpPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );

        jpDetallePedidos.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLLE PEDIDO"));

        jtDetallePedido.setAutoCreateRowSorter(true);
        jtDetallePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANTIDAD", "PERFIL", "COLOR", "LARGO", "P", "E", "SA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtDetallePedido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jtDetallePedido);
        jtDetallePedido.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jtDetallePedido.getColumnModel().getColumnCount() > 0) {
            jtDetallePedido.getColumnModel().getColumn(0).setMaxWidth(100);
            jtDetallePedido.getColumnModel().getColumn(1).setMaxWidth(100);
            jtDetallePedido.getColumnModel().getColumn(3).setMaxWidth(80);
            jtDetallePedido.getColumnModel().getColumn(4).setMaxWidth(30);
            jtDetallePedido.getColumnModel().getColumn(5).setMaxWidth(30);
            jtDetallePedido.getColumnModel().getColumn(6).setMaxWidth(30);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpDetallePedidosLayout = new javax.swing.GroupLayout(jpDetallePedidos);
        jpDetallePedidos.setLayout(jpDetallePedidosLayout);
        jpDetallePedidosLayout.setHorizontalGroup(
            jpDetallePedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetallePedidosLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jpDetallePedidosLayout.setVerticalGroup(
            jpDetallePedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpDetallePedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpColores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDetallePedidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbColoresBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbColoresBuscarActionPerformed
        buscarColores();
    }//GEN-LAST:event_jbColoresBuscarActionPerformed

    private void jbColoresQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbColoresQuitarActionPerformed
        quitarColorSeleccionado();
    }//GEN-LAST:event_jbColoresQuitarActionPerformed

    private void jbClientesBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbClientesBuscarActionPerformed
        buscarClientes();
    }//GEN-LAST:event_jbClientesBuscarActionPerformed

    private void jbClientesQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbClientesQuitarActionPerformed
        quitarClienteSeleccionado();
    }//GEN-LAST:event_jbClientesQuitarActionPerformed

    private void jtPedidosQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPedidosQuitarActionPerformed
        quitarPedidoSeleccionado();
    }//GEN-LAST:event_jtPedidosQuitarActionPerformed

    private void jtPedidosBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPedidosBuscarActionPerformed
        buscarPedidos();
    }//GEN-LAST:event_jtPedidosBuscarActionPerformed

    private void jbVerPerfiles_x_ColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVerPerfiles_x_ColorActionPerformed
        cargarListaPerfileColor();
    }//GEN-LAST:event_jbVerPerfiles_x_ColorActionPerformed

    private void jbLimpiarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarListaActionPerformed
        colores = null;
        cargarTablaColores(colores);
        buscarClientes();
    }//GEN-LAST:event_jbLimpiarListaActionPerformed

    private void jbPretratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPretratamientoActionPerformed
        cargarListaPerfilePretratar();
    }//GEN-LAST:event_jbPretratamientoActionPerformed

    private void jbPintarStocKBlancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPintarStocKBlancoActionPerformed
        cargarListaPintarStockBlanco();
    }//GEN-LAST:event_jbPintarStocKBlancoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmBuscarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBuscarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBuscarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBuscarPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBuscarPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JButton jbClientesBuscar;
    private javax.swing.JButton jbClientesQuitar;
    private javax.swing.JButton jbColoresBuscar;
    private javax.swing.JButton jbColoresQuitar;
    private javax.swing.JButton jbLimpiarLista;
    private javax.swing.JButton jbPintarStocKBlanco;
    private javax.swing.JButton jbPretratamiento;
    private javax.swing.JButton jbVerPerfiles_x_Color;
    private javax.swing.JPanel jpClientes;
    private javax.swing.JPanel jpColores;
    private javax.swing.JPanel jpDetallePedidos;
    private javax.swing.JPanel jpPedidos;
    private javax.swing.JTable jtClientes;
    private javax.swing.JTable jtColores;
    private javax.swing.JTable jtDetallePedido;
    private javax.swing.JTable jtPedidos;
    private javax.swing.JButton jtPedidosBuscar;
    private javax.swing.JButton jtPedidosQuitar;
    // End of variables declaration//GEN-END:variables
}
