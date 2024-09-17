/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ronald J
 */
public class MenuPanel extends JPanel{

    public MenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Diseño vertical
        setBackground(Color.WHITE); // Fondo blanco, por ejemplo
        setPreferredSize(new Dimension(140, 0)); // Fijar el tamaño inicial del menú
        setVisible(true); // Asegurar que el menú sea visible al iniciar
    }
}
