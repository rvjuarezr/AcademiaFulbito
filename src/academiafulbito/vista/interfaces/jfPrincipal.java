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

import academiafulbito.vista.utilidades.Utils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

    public jfPrincipal() {
        setUndecorated(false);// Configura la ventana sin bordes
        initComponents();

        // Maximizar la ventana para ocupar toda la pantalla
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        utils = new Utils(12);

        jpCabecera.setSize(jpPrincipal.getWidth(), jpCabecera.getHeight());//ajustar el ancho del panel de la cabecera con el mismo del JFrame
        // Repintar para que se apliquen los cambios
        jpCabecera.revalidate();
        jpCabecera.repaint();

        setLocationRelativeTo(null);
        jpMenuVertical.setLayout(new BoxLayout(jpMenuVertical, BoxLayout.Y_AXIS));// Disposición vertical
        jpMenuVertical.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//margen

        // Crear botones para el menú
        JButton btnEmpresa = createMenuButton("   EMPRESA      ", "/academiafulbito/vista/imagenes/inicio.png");
        JButton btnCategoria = createMenuButton("   CATEGORIA   ", "/academiafulbito/vista/imagenes/maestras.png");
        JButton btnCancha = createMenuButton("   CANCHA   ", "/academiafulbito/vista/imagenes/maestras/categorias.png");
        JButton btnCampeonato = createMenuButton("   CAMPEONATO   ", "/academiafulbito/vista/imagenes/maestras/categorias.png");
        JButton btnPadre = createMenuButton("   PADRES   ", "/academiafulbito/vista/imagenes/maestras/categorias.png");
        JButton btnProfesor = createMenuButton("   PROFESORES   ", "/academiafulbito/vista/imagenes/maestras/categorias.png");

        // Agregar botones al JPanel
        jpMenuVertical.add(btnEmpresa);
        jpMenuVertical.add(Box.createRigidArea(new Dimension(0, 4))); // Espacio entre botones
        jpMenuVertical.add(btnCategoria);
        jpMenuVertical.add(Box.createRigidArea(new Dimension(0, 4)));
        jpMenuVertical.add(btnCancha);
        jpMenuVertical.add(Box.createRigidArea(new Dimension(0, 4)));
        jpMenuVertical.add(btnCampeonato);
        jpMenuVertical.add(btnPadre);
        jpMenuVertical.add(Box.createRigidArea(new Dimension(0, 4)));
        jpMenuVertical.add(btnProfesor);
        jpMenuVertical.add(Box.createRigidArea(new Dimension(0, 4)));

        btnCategoria.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuCategorias == null || menuCategorias.isClosed()) {
                    menuCategorias = new jifCategorias(jdpAcademias);
                    jdpAcademias.add(menuCategorias);
                }
                menuCategorias.setLocation(utils.posicionX(menuCategorias, jdpAcademias), utils.posicionY(menuCategorias, jdpAcademias));
                menuCategorias.show();
                menuCategorias.toFront();
            }
        });

        btnCancha.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuCanchas == null || menuCanchas.isClosed()) {
                    menuCanchas = new jifCanchas(jdpAcademias);
                    jdpAcademias.add(menuCanchas);
                }
                menuCanchas.setLocation(utils.posicionX(menuCanchas, jdpAcademias), utils.posicionY(menuCanchas, jdpAcademias));
                menuCanchas.show();
                menuCanchas.toFront();
            }
        });
        btnCampeonato.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuCampeonatos == null || menuCampeonatos.isClosed()) {
                    menuCampeonatos = new jifCampeonatos(jdpAcademias);
                    jdpAcademias.add(menuCampeonatos);
                }
                menuCampeonatos.setLocation(utils.posicionX(menuCampeonatos, jdpAcademias), utils.posicionY(menuCampeonatos, jdpAcademias));
                menuCampeonatos.show();
                menuCampeonatos.toFront();
            }
        });

        btnPadre.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuPadres == null || menuPadres.isClosed()) {
                    menuPadres = new jifPadres(jdpAcademias);
                    jdpAcademias.add(menuPadres);
                }
                menuPadres.setLocation(utils.posicionX(menuPadres, jdpAcademias), utils.posicionY(menuPadres, jdpAcademias));
                menuPadres.show();
                menuPadres.toFront();
            }
        });

        btnProfesor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuProfesores == null || menuProfesores.isClosed()) {
                    menuProfesores = new jifProfesores(jdpAcademias);
                    jdpAcademias.add(menuProfesores);
                }
                menuProfesores.setLocation(utils.posicionX(menuProfesores, jdpAcademias), utils.posicionY(menuProfesores, jdpAcademias));
                menuProfesores.show();
                menuProfesores.toFront();
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

        jpPrincipal = new javax.swing.JPanel();
        jpCabecera = new javax.swing.JPanel();
        lblSesionUsuario = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        jpMenuVertical = new javax.swing.JPanel();
        jpVentanas = new javax.swing.JPanel();
        jdpAcademias = new javax.swing.JDesktopPane();
        jpMostrarOcultar = new javax.swing.JPanel();
        lblMostrarMenu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpCabecera.setBackground(new java.awt.Color(50, 121, 193));
        jpCabecera.setLayout(new java.awt.BorderLayout());

        lblSesionUsuario.setFont(new java.awt.Font("Bookman Old Style", 3, 24));
        lblSesionUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblSesionUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSesionUsuario.setText("HOLA : ");
        jpCabecera.add(lblSesionUsuario, java.awt.BorderLayout.EAST);

        lblLogo.setText(" ");
        jpCabecera.add(lblLogo, java.awt.BorderLayout.WEST);

        jpPrincipal.add(jpCabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 1150, 40));

        jpMenuVertical.setBackground(new java.awt.Color(255, 255, 255));
        jpMenuVertical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpPrincipal.add(jpMenuVertical, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 140, 440));

        jpVentanas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpVentanas.add(jdpAcademias, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, 0, 1290, 440));

        jpPrincipal.add(jpVentanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 1150, 440));

        jpMostrarOcultar.setBackground(new java.awt.Color(50, 121, 193));
        jpMostrarOcultar.setLayout(new java.awt.BorderLayout());

        lblMostrarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/academiafulbito/vista/imagenes/menu-mostrar.png"))); // NOI18N
        lblMostrarMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMostrarMenuMouseClicked(evt);
            }
        });
        jpMostrarOcultar.add(lblMostrarMenu, java.awt.BorderLayout.CENTER);

        jpPrincipal.add(jpMostrarOcultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 40));

        getContentPane().add(jpPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMostrarMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMostrarMenuMouseClicked
        // TODO add your handling code here:
        toggleMenu();
    }//GEN-LAST:event_lblMostrarMenuMouseClicked

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
    private javax.swing.JDesktopPane jdpAcademias;
    private javax.swing.JPanel jpCabecera;
    private javax.swing.JPanel jpMenuVertical;
    private javax.swing.JPanel jpMostrarOcultar;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpVentanas;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMostrarMenu;
    public static javax.swing.JLabel lblSesionUsuario;
    // End of variables declaration//GEN-END:variables

    private JButton createMenuButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false); // Hace que el botón sea transparente
        button.setOpaque(true); // Permite el uso de un color de fondo
        button.setBackground(Color.WHITE); // Establece el fondo blanco
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80)); // Ajusta el tamaño del botón
        return button;
    }


    // Método para mostrar/ocultar el menú
    private void toggleMenu() {
        if (jpMenuVertical.isVisible()) {
            jpMenuVertical.setVisible(false);
        } else {
            jpMenuVertical.setVisible(true);
        }
        jpPrincipal.revalidate();
        jpPrincipal.repaint();
    }
}
