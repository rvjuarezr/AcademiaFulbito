/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ronald J
 */
public class CabeceraPanel extends JPanel {

    public CabeceraPanel(JLabel lblSesionUsuario) {
        setLayout(new BorderLayout());
        setBackground(new java.awt.Color(218, 218, 218));
        setPreferredSize(new Dimension(0, 50)); // Fijar altura

        lblSesionUsuario.setFont(new Font(LiteralesTexto.LITERAL_BOOKMAN_OLD_STYLE, Font.PLAIN, 24));
        lblSesionUsuario.setForeground(new Color(128, 128, 128));

        add(lblSesionUsuario, BorderLayout.EAST);// Usuario en la derecha
    }
}
