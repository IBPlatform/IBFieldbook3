package ibfb.r.core;

import ibfb.r.ui.JDEspera;

public class MuestraEspera_Thread extends Thread {

    public JDEspera espera = new JDEspera(null, true);

    @Override
    public void run() {
        espera.setLocationRelativeTo(null);
        espera.setVisible(true);
    }
}
