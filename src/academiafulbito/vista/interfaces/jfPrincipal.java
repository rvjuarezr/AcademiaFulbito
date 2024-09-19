/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jfPrincipal.java
 *
 * Created on 14/08/2024, 12:41:52 AM
 */
package academiafulbito.vista.interfaces;

import academiafulbito.vista.utilidades.CabeceraPanel;
import academiafulbito.vista.utilidades.LiteralesTexto;
import academiafulbito.vista.utilidades.MenuPanel;
import academiafulbito.vista.utilidades.Utils;
import academiafulbito.vista.utilidades.VentanaManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import javax.swing.SwingConstants;

/**
 *
 * @author Ronald J
 */
public class jfPrincipal extends javax.swing.JFrame {

    /** Creates new form jfPrincipal */
    public static Utils utils;

    public static jifCategorias menuCategorias;
    public static jifCanchas menuCanchas;
    public static jifCampeonatos menuCampeonatos;
    public static jifPadres menuPadres;
    public static jifProfesores menuProfesores;
    public static jifHorario menuHorario;
    public static jifLugarEntrenamiento menuLugarEntrenamiento;

    private JPopupMenu pmMaestras, pmControl;
    private JPopupMenu subMenuActual = null; // Para mantener el submenú visible

    private JPanel jpCabecera, jpMenuVertical;
    public static JLabel lblSesionUsuario;
    private JDesktopPane jdpVentanas;
    private boolean menuVisible = true; // Controla la visibilidad del menú
    private JLayeredPane layeredPane;

    public jfPrincipal() {
        setUndecorated(false);// Configura la ventana sin bordes
        initComponents();

        // Maximizar la ventana del JFrame para ocupar toda la pantalla
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        utils = new Utils(12);

        // Cabecera
        setupCabecera();        

        // Panel vertical de menú
        setupMenuVertical();
        
        // JDesktopPane para mostrar los InternalFrames
        setupDesktopPane();

        //panel Layered
        setupMainLayout(); 

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (menuVisible) {
                    jpMenuVertical.setBounds(0, 0, 140, getHeight()); // Mantener el tamaño del menú cuando esté visible
                    jdpVentanas.setBounds(140, 0, getWidth() - 140, getHeight()); // Ajustar el DesktopPane
                } else {
                    jdpVentanas.setBounds(0, 0, getWidth(), getHeight()); // Ocupar todo el espacio cuando el menú esté oculto
                }

            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ACADEMIA DE FULBITO");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new jfPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private JButton createMenuButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setContentAreaFilled(false); // Hace que el botón sea transparente
        button.setOpaque(true); // Permite el uso de un color de fondo
        button.setBackground(Color.WHITE); // Establece el fondo blanco
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100)); // Ajusta el tamaño del botón
        return button;
    }

    private JMenuItem createSubMenuItem(String text) {
        final JMenuItem item = new JMenuItem(text);
        item.setOpaque(true);
        item.setBorderPainted(true);
        item.setBackground(Color.WHITE);
        item.setPreferredSize(new Dimension(200, 60));
        item.setFont(new Font(LiteralesTexto.LITERAL_BOOKMAN_OLD_STYLE, Font.PLAIN, 18));

        // Cambiar color al pasar el ratón
        item.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                item.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                item.setBackground(Color.WHITE);
            }
        });
        return item;
    }

    private void showMenu(JButton btn, JPopupMenu jpm) {
        if (btn != null) {
            // Ocultar el submenú actual si está visible
            if (subMenuActual != null && subMenuActual.isVisible()) {
                subMenuActual.setVisible(false);
            }
            Point location = btn.getLocationOnScreen();
            jpm.setLocation(location.x + btn.getWidth(), location.y); // Muestra el Menú a la derecha del botón
            jpm.setVisible(true);// Mostrar el nuevo submenú
            subMenuActual = jpm;// Guardar referencia al submenú visible

            resetButtonBorders(jpMenuVertical);  // Resetea los colores de todos los botones
            btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));  // Cambia el borde del botón activo;  // Cambia el color del botón activo
            
        }
    }

    // Método para mostrar/ocultar el menú
    private void toggleMenu() {
        if (menuVisible) {
            // Ocultar el menú vertical
            jpMenuVertical.setVisible(false);
            // Ajustar el JDesktopPane para que ocupe todo el espacio disponible
            jdpVentanas.setBounds(0, 0, getWidth(), getHeight());
        } else {
            // Mostrar el menú vertical
            jpMenuVertical.setVisible(true);
            // Ajustar el JDesktopPane para dejar espacio para el menú
            jdpVentanas.setBounds(140, 0, getWidth() - 140, getHeight());
        }
        menuVisible = !menuVisible; // Alternar el estado de visibilidad del menú

        // Revalidar y repintar el layeredPane para reflejar los cambios
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    // Métodos para inicializar los componentes
    private void setupCabecera() {

        lblSesionUsuario = new JLabel("Usuario: Administrador", JLabel.RIGHT);
        jpCabecera = new CabeceraPanel(lblSesionUsuario); // Usa una clase separada

        // Botón de menú desplegable
        JButton btnToggleMenu = new JButton();
        btnToggleMenu.setIcon(new ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/menu-mostrar.png")));
        btnToggleMenu.setPreferredSize(new Dimension(140, 0)); // Fijar ancho

        jpCabecera.add(btnToggleMenu, BorderLayout.WEST); // Icono de menu desplegable a la izquierda

        btnToggleMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleMenu();
            }
        });

        add(jpCabecera, BorderLayout.NORTH);// Añadir cabecera en la parte superior
    }

    private void setupMenuVertical() {
        jpMenuVertical = new MenuPanel();
        // Añadir botones de menú
        addMenuButtons(jpMenuVertical);
    }

    private void addMenuButtons(final JPanel jpMenuVertical) {
        // Crear y añadir botones al menú vertical
        final JButton btnEmpresa = createMenuButton("   EMPRESA      ", "/academiafulbito/vista/imagenes/inicio.png");
        final JButton btnMaestras = createMenuButton("   MAESTRAS      ", "/academiafulbito/vista/imagenes/maestras.png");
        final JButton btnControl = createMenuButton("   CONTROL      ", "/academiafulbito/vista/imagenes/control.png");
        final JButton btnPagos = createMenuButton("   PAGOS      ", "/academiafulbito/vista/imagenes/pagos.png");
        final JButton btnReportes = createMenuButton("   REPORTES      ", "/academiafulbito/vista/imagenes/reportes.png");
        final JButton btnSalir = createMenuButton("   SALIR      ", "/academiafulbito/vista/imagenes/logout.jpg");

        // Inicializar los submenús solo una vez
        pmMaestras = new JPopupMenu();
        setupSubMenus(new String[]{"CATEGORIAS", "LUGAR E.", "CAMPEONATO", "PADRES", "PROFESORES"}, pmMaestras);
        pmControl = new JPopupMenu();
        setupSubMenus(new String[]{"HORARIOS", "CANCHA"}, pmControl);

        // Añadir funcionalidad a los botones
        btnMaestras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar submenú y ocultar el actual si está visible
                showMenu(btnMaestras, pmMaestras);                
            }
        });

        btnControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                // Mostrar submenú y ocultar el actual si está visible
                showMenu(btnControl, pmControl);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonBorders(jpMenuVertical);
                btnSalir.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
                salirDelSistema();
            }
        });

        // Añadir botones al panel de menú
        jpMenuVertical.add(btnEmpresa);
        jpMenuVertical.add(btnMaestras);
        jpMenuVertical.add(btnControl);
        jpMenuVertical.add(btnPagos);
        jpMenuVertical.add(btnReportes);
        jpMenuVertical.add(btnSalir);

    }

    private void setupSubMenus(String[] opciones, final JPopupMenu jpm){
        jpm.setLayout(new BoxLayout(jpm, BoxLayout.Y_AXIS));
        // Crear las opciones del submenú        
        for (final String opcion : opciones) {
            final JMenuItem item = createSubMenuItem(opcion);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ocultar el submenú
                    jpm.setVisible(false);
                    VentanaManager.openVentana(opcion, jdpVentanas, utils); // Usa una clase que maneje la apertura de ventanas

                    // Cambiar el color del item seleccionado
                    resetMenuColors(jpm);  // Reseteamos colores de otros menús
                    item.setBackground(Color.LIGHT_GRAY);  // Color del menú seleccionado
                }

            });
            jpm.add(item);
        }
    }

    private void resetMenuColors(JPopupMenu jpm) {
        for (Component comp : jpm.getComponents()) {
            if (comp instanceof JMenuItem) {
                JMenuItem menuItem = (JMenuItem) comp;
                menuItem.setBackground(Color.WHITE);
            }
        }
    }

    private void resetButtonBorders(JPanel jpMenuVertical) {
        // Recorremos todos los componentes del panel y restablecemos los colores
        for (Component comp : jpMenuVertical.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                button.setBorder(BorderFactory.createEmptyBorder());  // Restablece el borde a vacío
            }
        }
    }

    private void salirDelSistema(){
        int retorno=Utils.mensajeConfirmacion("DESEA REALMENTE SALIR DEL\n SISTEMA DE ACADEMIA DE FULBITO");
        switch(retorno){
            case JOptionPane.YES_OPTION:
                System.exit(0);
                break;
            case JOptionPane.NO_OPTION:
                break;
        }
    }

    private void setupMainLayout() {
        //Crear el JLayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);// Para posicionar los componentes manualmente

        // Agregar el DesktopPane al JLayeredPane
        jpMenuVertical.setBounds(0, 0, 140, getHeight());// Posición fija del panel vertical
        jdpVentanas.setBounds(100, 0, getWidth() - 140, getHeight());// Asegurar que el DesktopPane no se superponga con el menú

        // Añadir el panel vertical al JLayeredPane (sobre el DesktopPane)
        layeredPane.add(jpMenuVertical, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(jdpVentanas, JLayeredPane.DEFAULT_LAYER);

        add(layeredPane, BorderLayout.CENTER);
    }

    private void setupDesktopPane(){
        jdpVentanas = new JDesktopPane();
        jdpVentanas.setBackground(Color.WHITE); // Fondo blanco para las ventanas
    }

}
