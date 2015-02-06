package indumatics_plantapintura.data;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class frmEspera implements Runnable {

    private final Thread t;
    public JProgressBar pb = null;
    public JDialog ventana = new JDialog();

    public frmEspera() {
        t = new Thread(this);
        ventana.setSize(300, 30);
        ventana.setLocationRelativeTo(null);
        ventana.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        ventana.setUndecorated(true);
        ventana.setAlwaysOnTop(true);
        ventana.setLayout(new BorderLayout());
        pb = new JProgressBar();
        pb.setString("Procesando Consulta...");
        pb.setStringPainted(true);
        pb.setIndeterminate(true);
        ventana.add(pb);
        ventana.setVisible(true);
        t.start();
    }

    public void close() {
        t.interrupt();
        ventana.dispose();
    }

    @Override
    public void run() {
        while (true) {
            try {
                t.sleep(30);
            } catch (InterruptedException e) {
                if (ventana != null) {
                    ventana.dispose();
                }
                break;
            }
        }
    }
}
