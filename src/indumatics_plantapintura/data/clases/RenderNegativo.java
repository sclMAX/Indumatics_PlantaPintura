package indumatics_plantapintura.data.clases;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderNegativo extends DefaultTableCellRenderer {

    private final Font normal;
    private final Font resaltado;

    public RenderNegativo() {
        this.resaltado = new Font(this.getFont().getName(), Font.BOLD, 12);
        this.normal = new Font(this.getFont().getName(), Font.PLAIN, 11);
    }

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
                setCero();
            }
            if ((aProcesar < cantidad)) {
                setWarning();
            }

            if (valor < 0) {
                setNegativo();
            }

            switch (column) {
                case 0: // CANTIDAD
                    orden = (OrdenPinturaDetalle) value;
                    if (orden != null) {
                        if(orden.getCantidad() == 0){
                            setCero();
                        }
                        if (orden.getCantidad() > orden.getaProcesar()) {
                            setWarning();
                        }                        
                    }else{
                        setNegativo();
                    }
                    break;
                case 1: //A PROCESAR
                    valor = (int)value;
                    if(valor == 0){
                        setCero();
                    }
                    int stkTotal = (int)table.getValueAt(row, 15);
                    int pedNatutral = (int)table.getValueAt(row, 13);
                    if(valor > (stkTotal - pedNatutral)){
                        setStockFaltante();
                    }
                    break;
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
        this.setFont(resaltado);
    }

    private void setCero() {
        this.setOpaque(true);
        this.setBackground(java.awt.Color.DARK_GRAY);
        this.setForeground(java.awt.Color.WHITE);
        this.setFont(normal);
    }

    private void setWarning() {
        this.setOpaque(true);
        this.setBackground(java.awt.Color.ORANGE);
        this.setForeground(java.awt.Color.BLUE);
        this.setFont(resaltado);
    }

    private void setDefault() {
        this.setBackground(java.awt.Color.WHITE);
        this.setForeground(java.awt.Color.BLACK);
        this.setFont(normal);
    }

    private void setStockFaltante() {
        this.setOpaque(true);
        this.setBackground(java.awt.Color.PINK);
        this.setForeground(java.awt.Color.RED);
        this.setFont(resaltado);
    }
}
