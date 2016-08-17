/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author M!ke
 */
public class VentanaSeleccionPersonaje extends javax.swing.JFrame {

    /**
     * Creates new form VentanaMenuPrincipal
     */
    
    public static VentanaSeleccionPersonaje instancia = null;
    
    
    
    public static VentanaSeleccionPersonaje Singleton()
    {
        if(instancia == null)
        {
            instancia = new VentanaSeleccionPersonaje();
            instancia.initComponents();
            instancia.setLocationRelativeTo(null);// Centrar la ventana
            //instancia.labelJugar.setIcon(ImageIconDic.singleton().getImageIcon("jugar"));
            instancia.fondo.setIcon(ImageIconDic.singleton().getImageIcon("fondoMenuPrincipal"));
            instancia.labelHeroe.setIcon(ImageIconDic.singleton().getImageIcon("SeleccionHeroeHover"));
            instancia.labelHeroina.setIcon(ImageIconDic.singleton().getImageIcon("SeleccionHeroinaHover"));
            instancia.seleccionPersonaje.setIcon(ImageIconDic.singleton().getImageIcon("seleccionPersonaje"));
            instancia.seleccionPersonaje.setLocation(85, 40);
            instancia.repaint();
        }
        return instancia;
    }
    
    
    @Override
    public void paint(Graphics g)
    {
        //jPanel1.getGraphics().drawImage(BufferedImageDic.Singleton().imagen("Gates"), 0, 0, null);
        jPanel1.paintAll(jPanel1.getGraphics());
    }
    
    
    public VentanaSeleccionPersonaje() {
        initComponents();
        setIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelHeroe = new javax.swing.JLabel();
        labelHeroina = new javax.swing.JLabel();
        seleccionPersonaje = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laberinto de Creta");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        labelHeroe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelHeroeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelHeroeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelHeroeMousePressed(evt);
            }
        });
        labelHeroe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labelHeroeMouseMoved(evt);
            }
        });
        jPanel1.add(labelHeroe);
        labelHeroe.setBounds(80, 110, 120, 110);

        labelHeroina.setPreferredSize(new java.awt.Dimension(28, 14));
        labelHeroina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelHeroinaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labelHeroinaMousePressed(evt);
            }
        });
        labelHeroina.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                labelHeroinaMouseMoved(evt);
            }
        });
        jPanel1.add(labelHeroina);
        labelHeroina.setBounds(230, 110, 120, 110);
        jPanel1.add(seleccionPersonaje);
        seleccionPersonaje.setBounds(80, 40, 236, 57);

        fondo.setText("fondo");
        jPanel1.add(fondo);
        fondo.setBounds(0, 0, 400, 300);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelHeroinaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeroinaMousePressed
        // TODO add your handling code here:
        VentanaLaberinto1.nombreHeroe = "HeroinaSpriteCompleto";
        VentanaLaberinto2.nombreHeroe = "HeroinaSpriteCompleto";
        VentanaLaberinto3.nombreHeroe = "HeroinaSpriteCompleto";
        VentanaLaberinto1.hud = "hudHeroina";
        VentanaLaberinto2.hud = "hudHeroina";
        VentanaLaberinto3.hud = "hudHeroina";
        
        //System.out.println(VentanaLaberinto1.nombreHeroe);
        String botonJugarAudio = "load.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(botonJugarAudio);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioStream nivel1 = null;
        try {
            nivel1 = new AudioStream(in);
        } catch (IOException ex) {
            Logger.getLogger(VentanaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(VentanaConfiguracion.prenderMusica)
        {
            AudioPlayer.player.start(nivel1);
        }
        
        
        this.setVisible(false);
        try {
            try {
                Control.Singleton().setEstadoJuego(Control.EstadosJuego.Cargando);
            } catch (InterruptedException ex) {
                Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_labelHeroinaMousePressed

    private void labelHeroeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeroeMousePressed
        // TODO add your handling code here:
        VentanaLaberinto1.nombreHeroe = "heroe4";
        VentanaLaberinto2.nombreHeroe = "heroe4";
        VentanaLaberinto3.nombreHeroe = "heroe4";
        VentanaLaberinto1.hud = "hudHeroe";
        VentanaLaberinto2.hud = "hudHeroe";
        VentanaLaberinto3.hud = "hudHeroe";
        //System.out.println(VentanaLaberinto1.nombreHeroe);
        String botonJugarAudio = "load.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(botonJugarAudio);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioStream nivel1 = null;
        try {
            nivel1 = new AudioStream(in);
        } catch (IOException ex) {
            Logger.getLogger(VentanaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(VentanaConfiguracion.prenderMusica)
        {
            AudioPlayer.player.start(nivel1);
        }
        
        this.setVisible(false);
        try {
            try {
                Control.Singleton().setEstadoJuego(Control.EstadosJuego.Cargando);
            } catch (InterruptedException ex) {
                Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_labelHeroeMousePressed

    private void labelHeroeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeroeMouseEntered
        // TODO add your handling code here:
        //instancia.labelHeroe.setIcon(ImageIconDic.singleton().getImageIcon("SeleccionHeroeHover"));
    }//GEN-LAST:event_labelHeroeMouseEntered

    private void labelHeroeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeroeMouseExited
        // TODO add your handling code here:
        instancia.labelHeroe.setIcon(ImageIconDic.singleton().getImageIcon("SeleccionHeroeHover"));
    }//GEN-LAST:event_labelHeroeMouseExited

    private void labelHeroeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeroeMouseMoved
        // TODO add your handling code here:
        instancia.labelHeroe.setIcon(ImageIconDic.singleton().getImageIcon("SeleccionHeroe"));
    }//GEN-LAST:event_labelHeroeMouseMoved

    private void labelHeroinaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeroinaMouseMoved
        // TODO add your handling code here:
        instancia.labelHeroina.setIcon(ImageIconDic.singleton().getImageIcon("SeleccionHeroina"));
    }//GEN-LAST:event_labelHeroinaMouseMoved

    private void labelHeroinaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeroinaMouseExited
        // TODO add your handling code here:
        instancia.labelHeroina.setIcon(ImageIconDic.singleton().getImageIcon("SeleccionHeroinaHover"));
    }//GEN-LAST:event_labelHeroinaMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSeleccionPersonaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaSeleccionPersonaje().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelHeroe;
    private javax.swing.JLabel labelHeroina;
    private javax.swing.JLabel seleccionPersonaje;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icono/icono2.gif")));
    }
}
