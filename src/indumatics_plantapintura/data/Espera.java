package indumatics_plantapintura.data;

import indumatics_plantapintura.guis.jfProgressBar;

public class Espera extends jfProgressBar {

    private static Espera instance = null;
    

    private Espera() {
        super(null, false);
        setLocationRelativeTo(null);        
    }

    private static Espera getInstance() {
        if (instance == null) {
            instance = new Espera();
        }
        return instance;
    }

    public static void Mostrar() {
        
        Espera v = getInstance();
        v.setVisible(true);
        v.jProgressBar1.setVisible(false);
        v.jProgressBar1.setString("CONSULTANDO BASE DE DATOS... ESPERE PORFAVOR!");
        v.jProgressBar1.setVisible(true);
    }

    public static void Ocultar() {
        Espera v = getInstance();
        v.setVisible(false);
    }

}
