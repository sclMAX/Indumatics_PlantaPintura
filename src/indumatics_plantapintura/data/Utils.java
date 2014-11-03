package indumatics_plantapintura.data;

import javax.swing.JOptionPane;

public class Utils {

    public static void showError(String titulo, String msj) {
        JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
    }
}
