package indumatics_plantapintura.data.clases;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderNegativo extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        try {
            int valor = 0;
            int cantidad = 0;
            int aProcesar = 0;
            OrdenPinturaDetalle orden = null;
            if (column != 0) {
                valor = (int) value;
            } else {
                orden = (OrdenPinturaDetalle) value;
            }
            if (orden != null) {
                cantidad = orden.getCantidad();
                aProcesar = orden.getaProcesar();
            }
            setDefault();
            if ((valor == 0) && (column != 0)) {
                setComent();
            }
            if ((aProcesar < cantidad)) {
                setWarning();
            }

            
            if (valor < 0) {
                setNegativo();
            }
        } catch (Exception ex) {
            setDefault();
        }
        return this;
    }

    private void setNegativo() {
        this.setOpaque(true);
        this.setBackground(java.awt.Color.RED);
        this.setForeground(java.awt.Color.YELLOW);
    }

    private void setComent() {
        this.setOpaque(true);
        this.setBackground(java.awt.Color.DARK_GRAY);
        this.setForeground(java.awt.Color.WHITE);
    }

    private void setWarning() {
        this.setOpaque(true);
        this.setBackground(java.awt.Color.ORANGE);
        this.setForeground(java.awt.Color.BLUE);
    }

    private void setDefault() {
        this.setBackground(java.awt.Color.WHITE);
        this.setForeground(java.awt.Color.BLACK);
    }
}
