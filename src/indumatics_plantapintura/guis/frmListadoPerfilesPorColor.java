package indumatics_plantapintura.guis;

import indumatics_plantapintura.data.clases.OrdenPintura;
import indumatics_plantapintura.data.clases.OrdenPinturaDetalle;
import indumatics_plantapintura.data.clases.RenderNegativo;
import indumatics_plantapintura.data.providers.OrdenPinturaDP;
import java.awt.Cursor;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
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
public class frmListadoPerfilesPorColor extends javax.swing.JFrame {

    private OrdenPintura orden = null;
    private Set<OrdenPinturaDetalle> detalles = new HashSet<>();
    private OrdenPinturaDetalle oldDetalle = null;
    private OrdenPinturaDetalle tmpDetalle = null;
    private final ListSelectionModel lsmPedidos;

    public frmListadoPerfilesPorColor(Set<OrdenPinturaDetalle> detalles) {
        initComponents();
        this.detalles = detalles;
        cargarTablaDetalle();
        lsmPedidos = jtOrdenPinturaDetalle.getSelectionModel();
        lsmPedidos.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int rowSelected = jtOrdenPinturaDetalle.getSelectedRow(), idColumn = 0;
                    if (rowSelected >= 0) {
                        for (int i = 0; i < jtOrdenPinturaDetalle.getColumnCount(); i++) {
                            if ("CANT.".equals(jtOrdenPinturaDetalle.getColumnName(i))) {
                                idColumn = i;
                            }
                        }
                        tmpDetalle = (OrdenPinturaDetalle) jtOrdenPinturaDetalle.getValueAt(rowSelected, idColumn);
                        oldDetalle = tmpDetalle;
                        cargarDetalle();
                    }
                }
            }
        });

    }

    private void cargarDetalle() {
        if (tmpDetalle != null) {
            txtCantidad.setText(Integer.toString(tmpDetalle.getaProcesar()));
            txtPerfil.setText(tmpDetalle.getIdPerfil());
            txtColorOrigen.setText(tmpDetalle.getColorOrigen().toString());
            txtColorDestino.setText(tmpDetalle.getColorDestino().toString());
        } else {
            txtCantidad.setText("");
            txtPerfil.setText("");
            txtColorOrigen.setText("");
            txtColorDestino.setText("");
        }
    }

    private void cargarTablaDetalle() {
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DefaultTableModel dtm = (DefaultTableModel) jtOrdenPinturaDetalle.getModel();
            dtm.setRowCount(0);
            if (detalles != null) {
                for (OrdenPinturaDetalle detalle : detalles) {
                    Object[] obj = new Object[16];
                    obj[0] = detalle;
                    obj[1] = detalle.getaProcesar();
                    obj[2] = detalle.getPerfil();
                    obj[3] = detalle.getLargo();
                    obj[4] = detalle.getColorOrigen();
                    obj[5] = detalle.getStockColorOrigen();
                    obj[6] = detalle.getEnProcesoColorOrigen();
                    obj[7] = detalle.getStockColorOrigen() + detalle.getEnProcesoColorOrigen();
                    obj[8] = detalle.getColorDestino();
                    obj[9] = detalle.getStockColorDestino();
                    obj[10] = detalle.getEnProcesoColorDestino();
                    obj[11] = detalle.getStockColorDestino() + detalle.getEnProcesoColorDestino();
                    obj[12] = detalle.getStockNatural();
                    obj[13] = detalle.getPedidosNatural();
                    obj[14] = detalle.getStockDisponible();
                    obj[15] = detalle.getStockTotal();
                    dtm.addRow(obj);
                }
                jtOrdenPinturaDetalle.setDefaultRenderer(Object.class, new RenderNegativo());
                jtOrdenPinturaDetalle.setCellSelectionEnabled(false);
                jtOrdenPinturaDetalle.setRowSelectionAllowed(true);
            }
            jtOrdenPinturaDetalle.setModel(dtm);
        } finally {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private void generarOrden() {
        jfOrdenPintura altaOrden = new jfOrdenPintura(this, true);
        altaOrden.setVisible(true);
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            orden = altaOrden.getOrden();
            if (orden != null) {
                orden.setDetalles(detalles);
                try {
                    int nro = OrdenPinturaDP.add(orden);
                    jlNro.setText(Integer.toString(nro));
                } catch (SQLException ex) {
                    Logger.getLogger(frmListadoPerfilesPorColor.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "ERROR AL GUARDAR: "
                            + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } finally {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private void validarAProcesar() {
        if (detalles != null) {
            for (OrdenPinturaDetalle detalle : detalles) {
                detalle.validarCantidad();
            }
            cargarTablaDetalle();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jbGuardar = new javax.swing.JButton();
        jbDeshacer = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jbValidar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jbGenerarOrden = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jlNro = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPerfil = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtColorOrigen = new javax.swing.JTextField();
        txtColorDestino = new javax.swing.JTextField();
        jbCambiarColorOrigen = new javax.swing.JButton();
        jbCambiarColorDestino = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtOrdenPinturaDetalle = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTEMP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_save_24x24.gif"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("indumatics_plantapintura/recursos/strings"); // NOI18N
        jbGuardar.setToolTipText(bundle.getString("save")); // NOI18N
        jbGuardar.setFocusable(false);
        jbGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbGuardar);

        jbDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_undo_24x24.gif"))); // NOI18N
        jbDeshacer.setToolTipText(bundle.getString("undo")); // NOI18N
        jbDeshacer.setFocusable(false);
        jbDeshacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbDeshacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDeshacerActionPerformed(evt);
            }
        });
        jToolBar1.add(jbDeshacer);
        jToolBar1.add(jSeparator1);

        jbValidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_ok_24x24.gif"))); // NOI18N
        jbValidar.setToolTipText(bundle.getString("validarCantidad")); // NOI18N
        jbValidar.setFocusable(false);
        jbValidar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbValidar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbValidarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbValidar);
        jToolBar1.add(jSeparator2);

        jbGenerarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/ventas_24x24.gif"))); // NOI18N
        jbGenerarOrden.setToolTipText(bundle.getString("generar")); // NOI18N
        jbGenerarOrden.setFocusable(false);
        jbGenerarOrden.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbGenerarOrden.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbGenerarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGenerarOrdenActionPerformed(evt);
            }
        });
        jToolBar1.add(jbGenerarOrden);
        jToolBar1.add(jSeparator3);

        jButton1.setText("jButton1");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jlNro.setText("ORDEN NRO:");
        jToolBar1.add(jlNro);

        jLabel1.setText("CANTIDAD");

        jLabel2.setText("PREFIL");

        txtPerfil.setEditable(false);

        jLabel3.setText("COLOR ORIGEN");

        jLabel4.setText("COLOR DESTINO");

        txtColorOrigen.setEditable(false);

        txtColorDestino.setEditable(false);

        jbCambiarColorOrigen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_clip_16x16.gif"))); // NOI18N
        jbCambiarColorOrigen.setToolTipText("");
        jbCambiarColorOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCambiarColorOrigenActionPerformed(evt);
            }
        });

        jbCambiarColorDestino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/indumatics_plantapintura/recursos/iconos/btn_clip_16x16.gif"))); // NOI18N
        jbCambiarColorDestino.setToolTipText("");
        jbCambiarColorDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCambiarColorDestinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPerfil)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(txtColorOrigen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCambiarColorOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtColorDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCambiarColorDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbCambiarColorOrigen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCambiarColorDestino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtColorOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtColorDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jtOrdenPinturaDetalle.setAutoCreateRowSorter(true);
        jtOrdenPinturaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANT.", "A PRO.", "PERFIL", "LARGO", "COLOR ORIGEN", "STK C.O", "PRO C.O", "TOT C.O", "COLOR DESTINO", "STK C.D", "PRO C.D", "TOT C.D.", "STK NAT", "PED NAT", "STK DSP", "STK T."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtOrdenPinturaDetalle.setColumnSelectionAllowed(true);
        jtOrdenPinturaDetalle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jtOrdenPinturaDetalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtOrdenPinturaDetalle);
        jtOrdenPinturaDetalle.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jtOrdenPinturaDetalle.getColumnModel().getColumnCount() > 0) {
            jtOrdenPinturaDetalle.getColumnModel().getColumn(0).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(0).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(1).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(1).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(2).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(2).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(3).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(3).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(4).setMinWidth(100);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(5).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(5).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(6).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(6).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(7).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(7).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(8).setMinWidth(100);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(9).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(9).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(10).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(10).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(11).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(11).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(12).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(12).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(13).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(13).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(14).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(14).setPreferredWidth(40);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(15).setMinWidth(30);
            jtOrdenPinturaDetalle.getColumnModel().getColumn(15).setPreferredWidth(40);
        }

        jtTEMP.setAutoCreateRowSorter(true);
        jtTEMP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANT.", "A PRO.", "PERFIL", "LARGO", "COLOR ORIGEN", "STK C.O", "PRO C.O", "TOT C.O", "COLOR DESTINO", "STK C.D", "PRO C.D", "TOT C.D.", "STK NAT", "PED NAT", "STK DSP", "STK T."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTEMP.setRowSelectionAllowed(true);
        jtTEMP.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jtTEMP.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtTEMP);
        jtTEMP.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jtTEMP.getColumnModel().getColumnCount() > 0) {
            jtTEMP.getColumnModel().getColumn(0).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(0).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(1).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(1).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(2).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(2).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(3).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(3).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(4).setMinWidth(100);
            jtTEMP.getColumnModel().getColumn(5).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(5).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(6).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(6).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(7).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(7).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(8).setMinWidth(100);
            jtTEMP.getColumnModel().getColumn(9).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(9).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(10).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(10).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(11).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(11).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(12).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(12).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(13).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(13).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(14).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(14).setPreferredWidth(40);
            jtTEMP.getColumnModel().getColumn(15).setMinWidth(30);
            jtTEMP.getColumnModel().getColumn(15).setPreferredWidth(40);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        try {
            tmpDetalle.setaProcesar(Integer.parseInt(txtCantidad.getText()));
            if (detalles.remove(oldDetalle)) {
                oldDetalle = tmpDetalle;
                detalles.add(oldDetalle);
                cargarTablaDetalle();
            } else {
                JOptionPane.showMessageDialog(this, "No se gurado la informacion!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "CANTIDAD no valida!", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtCantidad.requestFocus();
        }
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDeshacerActionPerformed
        tmpDetalle = oldDetalle;
        cargarDetalle();
    }//GEN-LAST:event_jbDeshacerActionPerformed

    private void jbCambiarColorOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCambiarColorOrigenActionPerformed
        jfSelColor sc = new jfSelColor(this, true);
        sc.setVisible(true);
        if (sc.getColor() != null) {
            if (tmpDetalle != null) {
                tmpDetalle.setColorOrigen(sc.getColor());
                txtColorOrigen.setText(tmpDetalle.getColorOrigen().toString());
            }
        }
    }//GEN-LAST:event_jbCambiarColorOrigenActionPerformed

    private void jbCambiarColorDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCambiarColorDestinoActionPerformed
        jfSelColor sc = new jfSelColor(this, true);
        sc.setVisible(true);
        if (sc.getColor() != null) {
            if (tmpDetalle != null) {
                tmpDetalle.setColorDestino(sc.getColor());
                txtColorDestino.setText(tmpDetalle.getColorDestino().toString());
            }
        }
    }//GEN-LAST:event_jbCambiarColorDestinoActionPerformed

    private void jbGenerarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGenerarOrdenActionPerformed
        generarOrden();
    }//GEN-LAST:event_jbGenerarOrdenActionPerformed

    private void jbValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbValidarActionPerformed
        validarAProcesar();
    }//GEN-LAST:event_jbValidarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jtTEMP.setModel(jtOrdenPinturaDetalle.getModel());
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmListadoPerfilesPorColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmListadoPerfilesPorColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmListadoPerfilesPorColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmListadoPerfilesPorColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmListadoPerfilesPorColor(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbCambiarColorDestino;
    private javax.swing.JButton jbCambiarColorOrigen;
    private javax.swing.JButton jbDeshacer;
    private javax.swing.JButton jbGenerarOrden;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbValidar;
    private javax.swing.JLabel jlNro;
    private javax.swing.JTable jtOrdenPinturaDetalle;
    private javax.swing.JTable jtTEMP;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtColorDestino;
    private javax.swing.JTextField txtColorOrigen;
    private javax.swing.JTextField txtPerfil;
    // End of variables declaration//GEN-END:variables
}
