/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Ronald J
 */
public class DialogUtils {

    // Método para mostrar información en un diálogo
    public static void mostrarInformacion(String nombreBoton, String titulo, Map<String, String> datos, int tamanioLetra, int longitudCadena) {
        // Configurar el tamaño y estilo del botón "Aceptar"
        Utils.aplicarEstiloButtonJOptionPane(nombreBoton, 25, 150, 70);

        // Crear un panel personalizado para el JOptionPane
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado alrededor de cada componente
        gbc.anchor = GridBagConstraints.WEST; // Alinea a la izquierda
        gbc.fill = GridBagConstraints.HORIZONTAL; // Permite que el componente ocupe el espacio horizontal

        // Agregar datos al panel
        int fila = 0;
        for (Map.Entry<String, String> entry : datos.entrySet()) {
            gbc.gridy = fila++;
            panel.add(Utils.createStyledLabel(entry.getKey(), entry.getValue(), tamanioLetra, longitudCadena), gbc);
        }

        // Configurar el borde redondeado
        int radius = 30; // Radio de los bordes redondeados
        Border roundedBorder = new RoundedBorder(radius, new Color(103, 98, 98), 2);
        Border emptyBorder = new EmptyBorder(10, 10, 10, 10); // Espaciado interno
        Border compoundBorder = new CompoundBorder(roundedBorder, emptyBorder);
        panel.setBorder(compoundBorder);

        // Crear un panel con el título personalizado
        JPanel dialogPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(titulo, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 20)); // Cambiar el tamaño y estilo del título
        titleLabel.setForeground(new Color(103, 98, 98));
        dialogPanel.add(titleLabel, BorderLayout.NORTH);
        dialogPanel.add(panel, BorderLayout.CENTER);

        // Crear el JOptionPane
        JOptionPane optionPane = new JOptionPane(dialogPanel, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);

        // Crear un JDialog y configurarlo
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setLocationRelativeTo(null); // Centrar el diálogo en la pantalla
        dialog.setVisible(true);
    }
}
