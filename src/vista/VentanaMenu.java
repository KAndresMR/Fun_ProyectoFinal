
package vista;

import java.io.IOException;
import java.sql.SQLException;
import vista.Login;
import javax.swing.SwingUtilities;


public class VentanaMenu extends javax.swing.JFrame 
{
    Login pnlLogin;
    VistaAdministrador admin;
    
    
    public VentanaMenu() throws SQLException, IOException {
        
        initComponents();
        this.pnlLogin = new Login();
        this.admin = new VistaAdministrador();
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnPanel = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        getContentPane().add(escritorio);

        jMenu1.setText("Modulos");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        mnPanel.setText("Login");
        mnPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPanelActionPerformed(evt);
            }
        });
        jMenu1.add(mnPanel);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("VistaAdmin");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void mnPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPanelActionPerformed
        getContentPane().remove(escritorio);
        getContentPane().add(pnlLogin);
            
        actualizarPantalla();
        
        
    }//GEN-LAST:event_mnPanelActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        getContentPane().remove(escritorio);
        getContentPane().add(admin);
        actualizarPantalla();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    public void cargarAdmin(){
        getContentPane().remove(pnlLogin);
        getContentPane().add(admin);
        actualizarPantalla();
    }
    public void actualizarPantalla()
    {
        SwingUtilities.updateComponentTreeUI(this);
    }        
            

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mnPanel;
    // End of variables declaration//GEN-END:variables
}
