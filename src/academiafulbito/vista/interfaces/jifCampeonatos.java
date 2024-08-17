/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jifCategorias.java
 *
 * Created on 02/08/2024, 02:42:40 PM
 */

package academiafulbito.vista.interfaces;

import academiafulbito.controlador.beans.CampeonatoFacade;
import academiafulbito.modelo.entidades.Campeonato;
import academiafulbito.vista.utilidades.ButtonEditor;
import academiafulbito.vista.utilidades.ButtonRenderer;
import academiafulbito.vista.utilidades.Utils;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald J
 */
public class jifCampeonatos extends javax.swing.JInternalFrame {

    JDesktopPane jdp;
    public static CampeonatoFacade campeonatoFacade;
    DefaultTableModel modelo;
    /** Creates new form jifCategorias */
    public jifCampeonatos(JDesktopPane jdpModAF){
        initComponents();
        jdp=jdpModAF;
        int[] anchoColumnas = {15,80, 20, 80}; // Anchos específicos para cada columna
        jfPrincipal.utils.setAnchoColumnas(tblCampeonatos, anchoColumnas);
        jfPrincipal.utils.ocultarColumnas(tblCampeonatos, 0);
        campeonatoFacade = new CampeonatoFacade();
        listarCampeonatos(campeonatoFacade.getListadoCampeonatos());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tphCampeonatos = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        panel1 = new org.edisoncor.gui.panel.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCampeonatos = new javax.swing.JTable();
        panel2 = new org.edisoncor.gui.panel.Panel();

        setClosable(true);
        setTitle("MANTENIMIENTO CAMPEONATOS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setColorPrimario(new java.awt.Color(255, 255, 255));
        panel1.setColorSecundario(new java.awt.Color(51, 51, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCampeonatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "CAMPEONATO", "TEMPORADA", "Acciones"
            }
        ));
        jScrollPane1.setViewportView(tblCampeonatos);

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 620, 250));

        tphCampeonatos.addTab("LISTADO", panel1);

        panel2.setColorPrimario(new java.awt.Color(255, 255, 255));
        panel2.setColorSecundario(new java.awt.Color(51, 51, 255));
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tphCampeonatos.addTab("CAMPEONATOS", panel2);

        getContentPane().add(tphCampeonatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel2;
    private javax.swing.JTable tblCampeonatos;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader tphCampeonatos;
    // End of variables declaration//GEN-END:variables

    private void listarCampeonatos(List<Campeonato> listaCampeonatos){
        // Selecciona el primer tab en un JTabbedPane
        tphCampeonatos.setSelectedIndex(0);

        // Obtiene el modelo de la tabla
        modelo=(DefaultTableModel)tblCampeonatos.getModel();

        // limpia los datos existentes en la tabla.
        Utils.limpiarModeloTabla(modelo, tblCampeonatos);

        // Verificar si la lista de socios tiene elementos
        if (listaCampeonatos.size() > 0) {
            System.out.println("LISTADO DE CAMPEONATOS DESDE LA BBDD");
            // Iterar sobre la lista de categorias y agregar cada categoria a la tabla
            for (Campeonato campeonato : listaCampeonatos) {

                System.out.println("campeonato.getIdCampeonato():"+campeonato.getIdCampeonato()+" ,campeonato.getNombre():"+campeonato.getNombre()+" ,campeonato.getTemporada():"+campeonato.getTemporada());

                // Crea un array de objetos con los datos de la categoria para agregar a la tabla.
                Object[] fila = new Object[]{
                    campeonato.getIdCampeonato(),
                    campeonato.getNombre(),
                    campeonato.getTemporada()
                };
                modelo.addRow(fila); // Agregar la fila al modelo de la tabla
            }
            // Establece un renderizador personalizado para las celdas de la tabla.
            tblCampeonatos.setDefaultRenderer(Object.class, new Utils(18));

            // Agregar botones en la última columna
            tblCampeonatos.getColumn("Acciones").setCellRenderer(new ButtonRenderer("Acciones"));
            tblCampeonatos.getColumn("Acciones").setCellEditor(new ButtonEditor("Acciones"));

            // Establece el modo de selección de filas para permitir solo una selección a la vez.
            tblCampeonatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        }
    }
}
