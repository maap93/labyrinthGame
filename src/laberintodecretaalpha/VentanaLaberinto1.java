/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M!ke
 */
public class VentanaLaberinto1 extends javax.swing.JFrame implements KeyListener {

    /**
     * Creates new form VentanaLaberinto
     */
    public static String nombreHeroe = "";
    public static String hud = "";
    public static boolean activo = true;
    public static String estado = "";
    public static boolean nivelCargado = false;
    
    private final int velocidadFondo = 0;
    private int inicioFondo = 0;
    private BufferedImage fondo = null;
    private static final int tileSize = 32;
    private static final int anchoNivel = 640;
    private static final int altoNivel = 200;
    private static final int numeroTiles = 30;
    private final BufferedImage[] tiles = new BufferedImage[numeroTiles];
    private final int[][] nivel = new int[anchoNivel][altoNivel];
    private int columnaVisible = 0;
    private final int columnas = 20;
    private int filaVisible = 40;
    private final int filas = 40;
    private static VentanaLaberinto1 instancia = null;
    private int tileX = 6;
    private int tileY = 45;
    private int minE = 30;
    private int maxE = 180;
    private int minE2 = 90;
    private int maxE2 = 200;
    private int minE3 = 90;
    private int maxE3 = 200;
    
    
    
    
    private boolean colision1 = false;
    private boolean colision2 = false;
    private Cronometro tiempo = new Cronometro(4);
    // private int[][] 
    boolean A = false, W = false, S = false, D = false, UP = false, DOWN = false, RIGHT = false, LEFT = false, SPACE = false;
    private Nivel1 juego = new Nivel1();

    public Nivel1 getJuego() {
        return juego;
    }

    public void setJuego(Nivel1 juego) {
        this.juego = juego;
    }

    
    public static VentanaLaberinto1 Singleton() throws FileNotFoundException, IOException {
        if (instancia == null) 
        {
            
            instancia = new VentanaLaberinto1();
            instancia.initComponents();
            instancia.createBufferStrategy(2);
            instancia.setLocationRelativeTo(null); //Centrar la ventana
            instancia.setResizable(false);
            instancia.juego.setPersonaje(nombreHeroe, 12);
            instancia.juego.setEnemigo("esqueleto", 12);
            instancia.juego.setHud(hud);
            instancia.juego.setVidas("vidaIntento2");
            instancia.juego.setVidasNegativas("quitarVida");
            instancia.juego.setProyectil();
        }
        return instancia;
    }
    
    //Reiniciar
    public void clear()
    {
        instancia = null;
    }
    
    //Pruebas
    public VentanaLaberinto1() {
        initComponents();
        setIcon();
    }
    
    //Fin Pruebas

    @Override
    public void paint(Graphics g) {
        try {
            //BufferStrategy prepara = Singleton().getBufferStrategy();
            //Graphics dibujar = prepara.getDrawGraphics();
            //dibujar.setColor(Color.BLACK);




            //desplazaFondo(dibujar);

            //desplazaFondo(dibujar);


            //heroe.actualiza(dibujar);

            //heroe.actualiza(dibujar);

            juego.actualiza(this,tileY);
            juego.esqueleto.patrullaY(maxE, minE);
            juego.escorpion.patrullaX(maxE2,minE2);
            juego.sombra.patrullaX(maxE3, minE3);
            //if(juego.animado.getPosicionY() == juego.esqueleto.getPosicionY() && juego.esqueleto.getPosicionX() == 194)
            if((juego.animado.getPosicionY() == juego.esqueleto.getPosicionY() && juego.esqueleto.getPosicionX() == 194) || (juego.animado.getPosicionX() == juego.escorpion.getPosicionX() && juego.escorpion.getPosicionY() == 146))
            {
                colision1 = true;
                //System.out.println("asdasd");
            }else{
                colision1 = false;
            }
            
            if(tileY <= 10)
            {
                if(juego.sombra.getPosicionX() == 182 && juego.sombra.getPosicionY() == 160)
                {
                    colision1=true;
                }
                else
                {
                    colision1 = false;
                }
            }
            
            
            if(tiempo.esTiempo()){
                if(colision1)
                {
                    Vidas.setNumVidas(Vidas.getNumVidas() - 1);
                    if(Vidas.getNumVidas() <= 0)
                    {
                        Vidas.setNumVidas(0);
                        try {
                            Control.Singleton().setEstadoJuego(Control.EstadosJuego.GameOver);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        VentanaLaberinto1.Singleton().setVisible(false);
                        
                    }
                }
            }
            //System.out.println("posanimado: "+juego.animado.getPosicionX());
            //System.out.println("posesquelteo: "+juego.esqueleto.getPosicionX());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargaNivel(String nombreNivel) {
        try {
            nivelCargado=true;
            FileReader input = new FileReader("niveles/" + nombreNivel);
            try (BufferedReader bufRead = new BufferedReader(input)) {
                String linea = bufRead.readLine();
                tiles[0] = null;
                for (int i = 1; i < numeroTiles; i++) {
                    tiles[i] = BufferedImageDic.Singleton().imagen("tile" + i);
                }

                //	Primera	linea:	nombre	de	la	imagen	de	fondo
                if (linea != null) {
                    fondo = BufferedImageDic.Singleton().imagen(linea);
                }
                int col = 0;
                int ren = 0;
                //	Lineas	subsecuentes:	mapa	de	tiles	a	cargar	en	la	matriz	nivel
                while ((linea = bufRead.readLine()) != null) {
                    StringTokenizer tokens = new StringTokenizer(linea);
                    while (tokens.hasMoreTokens()) {
                        String token = tokens.nextToken();
                        nivel[col][ren] = Integer.parseInt(token);
                        col++;
                    }
                    ren++;
                    col = 0;
                }
            }
        } catch (Exception e) {
            //System.out.println(e);
        }

    }

    /* public int MapeaTiles() {
     int x = 0, y = 0;
     for (int i = columnaVisible; i < columnaVisible + columnas; i++) {

     for (int j = filaVisible; j < filaVisible + filas; j++) {
                
     y += tileSize;
     }
     y = 0;
     x += tileSize;
     }
        
     return nivel[filaVisible+7][columnaVisible+4];
     } */
    public void pintaTiles(Graphics contexto) {
        int x = 0, y = 0;
        //   System.out.println(this.MapeaTiles());

        for (int i = columnaVisible; i < columnaVisible + columnas; i++) {

            for (int j = filaVisible; j < filaVisible + filas; j++) {
                contexto.drawImage(tiles[nivel[i][j]], x, y, null);
                y += tileSize;
            }
            y = 0;
            x += tileSize;
        }

        // System.out.println("tile actual: " + this.devuelveTile());


    }
    int b = 0;
    int aux = 0;

    public void desplazaFondo(Graphics contexto) throws FileNotFoundException, IOException {

        BufferedImage izquierda = null;
        BufferedImage derecha = null;
        BufferedImage arriba = null;
        BufferedImage abajo = null;

        if (inicioFondo < fondo.getWidth()) {
            izquierda = fondo.getSubimage(inicioFondo, 0, fondo.getWidth() - inicioFondo, fondo.getHeight());
            contexto.drawImage(izquierda, 0, 0, VentanaLaberinto1.Singleton());
        }
        if (inicioFondo > 0) {
            derecha = fondo.getSubimage(0, 0, inicioFondo, fondo.getHeight());
            contexto.drawImage(derecha, fondo.getWidth() - inicioFondo, 0, VentanaLaberinto1.Singleton());
        }
        if (inicioFondo < fondo.getWidth()) {
            arriba = fondo.getSubimage(0, inicioFondo, fondo.getWidth(), fondo.getHeight() - inicioFondo);
            contexto.drawImage(arriba, 0, 0, VentanaLaberinto1.Singleton());
        }
        if (inicioFondo > 0) {
            abajo = fondo.getSubimage(0, 0, fondo.getWidth(), inicioFondo);
            contexto.drawImage(abajo, 0, fondo.getHeight() - inicioFondo, VentanaLaberinto1.Singleton());
        }
        inicioFondo += velocidadFondo;
        if (inicioFondo > fondo.getWidth()) {
            inicioFondo = 0;
        }
        if (inicioFondo > fondo.getHeight()) {
            inicioFondo = 0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laberinto de Creta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaLaberinto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaLaberinto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaLaberinto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaLaberinto1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaLaberinto1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent ke) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //System.exit(0);
            instancia.setVisible(false);
            //estado = Control.Singleton().getEstadojuego().toString();
            //System.out.println(estado);
            try {
                try {
                    Control.Singleton().setEstadoJuego(Control.EstadosJuego.Pausa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
            }
            activo = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            juego.detectarColision();
            juego.animado.setDireccion(Personaje.Direccion.derecha);
            
            //System.out.println("Siguiente tile: " + nivel[tileX + 1][tileY]);
            if (nivel[tileX + 1][tileY] == 1 || nivel[tileX + 1][tileY] == 7) {
                this.tileX += 1;
                System.out.println("TileX"+tileX);
                if (columnaVisible < anchoNivel) {
                    juego.esqueleto.setPosicionX(juego.esqueleto.getPosicionX() - 32);
                    
                    if(tileY <= 10)
                    {
                        juego.sombra.setPosicionX(juego.sombra.getPosicionX() - 32);
                        this.maxE3 -= 32;
                        this.minE3 -= 32;
                        instancia.juego.sombra.patrullaX(maxE3, minE3);
                    }
                    juego.escorpion.setPosicionX(juego.escorpion.getPosicionX() - 32);
                    this.maxE2 -= 32;
                    this.minE2 -= 32;
                    instancia.juego.escorpion.patrullaX(maxE2, minE2);
                    columnaVisible++;
                    System.out.println("Columna visible"+ columnaVisible);
                    //instancia.juego.esqueleto.setPosicionX(instancia.juego.esqueleto.getPosicionX() - 1);

                }
            } else 
            {
                System.out.println("prueba");
                if(Vidas.getNumVidas()<=0)
            {
                    try {
                    try {
                        Control.Singleton().setEstadoJuego(Control.EstadosJuego.GameOver);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaLaberinto2.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                /*Vidas.setNumVidas(Vidas.getNumVidas() - 1);
                if(Vidas.getNumVidas() < 0)
                {
                    Vidas.setNumVidas(0);
                    
                }*/
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            juego.detectarColision();
            juego.animado.setDireccion(Personaje.Direccion.izquierda);
            System.out.println("entre"+Personaje.x);
            //System.out.println("Siguiente tile: " + nivel[tileX - 1][tileY]);
            if (nivel[tileX - 1][tileY] == 1 || nivel[tileX - 1][tileY] == 7) {
                this.tileX -= 1;
                if (columnaVisible > 0) {
                    columnaVisible--;
                    juego.esqueleto.setPosicionX(juego.esqueleto.getPosicionX() + 32);
                    
                    if(tileY <= 10)
                    {
                        instancia.juego.sombra.setPosicionX(instancia.juego.sombra.getPosicionX() + 32 );
                        this.maxE3 += 32;
                        this.minE3 += 32;
                    }
                    instancia.juego.escorpion.setPosicionX(instancia.juego.escorpion.getPosicionX() + 32);
                    this.maxE2 += 32;
                    this.minE2 += 32;
                    
                    
                    //instancia.juego.esqueleto.setPosicionX(instancia.juego.esqueleto.getPosicionX() + 1);
                }
            } else {
                if(Vidas.getNumVidas()<=0)
            {
                    try {
                    try {
                        Control.Singleton().setEstadoJuego(Control.EstadosJuego.GameOver);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaLaberinto2.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                if(Vidas.getNumVidas()<=0)
            {
                    try {
                    try {
                        Control.Singleton().setEstadoJuego(Control.EstadosJuego.GameOver);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaLaberinto2.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                /*Vidas.setNumVidas(Vidas.getNumVidas() - 1);
                if(Vidas.getNumVidas() < 0)
                {
                    Vidas.setNumVidas(0);
                }*/
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            juego.detectarColision();
            juego.animado.setDireccion(Personaje.Direccion.atras);
            System.out.println("entre "+Personaje.velocidad);
            //System.out.println("Siguiente tile: " + nivel[tileX][tileY - 1]);
            if (nivel[tileX][tileY - 1] == 1 || nivel[tileX][tileY - 1] == 7) {
                this.tileY -= 1;
                if (filaVisible > 0) 
                {
                    filaVisible--;
                    this.maxE += 32;
                    this.minE += 32;
                    instancia.juego.esqueleto.patrullaY(maxE, minE);
                    
                    if(tileY <= 10)
                    {
                        instancia.juego.sombra.setPosicionY(instancia.juego.sombra.getPosicionY() + 32);
                    }
                    
                    instancia.juego.escorpion.setPosicionY(instancia.juego.escorpion.getPosicionY() + 32);
                    
                    
                    //instancia.juego.esqueleto.setPosicionY(instancia.juego.esqueleto.getPosicionY() + 1);
                }
            } else 
            {
                
                if(nivel[tileX][tileY - 1]==15)
                {
                    try {
                        activo = false;
                        VentanaLaberinto1.Singleton().setVisible(false);
                        try {
                            Control.Singleton().setEstadoJuego(Control.EstadosJuego.Cargando);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    if(Vidas.getNumVidas()<=0)
            {
                    try {
                    try {
                        Control.Singleton().setEstadoJuego(Control.EstadosJuego.GameOver);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaLaberinto2.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                    /*Vidas.setNumVidas(Vidas.getNumVidas() - 1);
                    if(Vidas.getNumVidas() < 0)
                    {
                        Vidas.setNumVidas(0);
                    }*/
                }
                
                
            }

        }
        
        
        
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            juego.detectarColision();
            juego.animado.setDireccion(Personaje.Direccion.adelante);
            System.out.println("entre"+Personaje.x);
            //System.out.println("Siguiente tile: " + nivel[tileX][tileY + 1]);
            if (nivel[tileX][tileY + 1] == 1 || nivel[tileX][tileY + 1] == 7) {
                this.tileY += 1;
                if (filaVisible < altoNivel) {
                    filaVisible++;
                    this.maxE -= 32;
                    this.minE -= 32;
                    instancia.juego.esqueleto.patrullaY(maxE, minE);
                    
                    if(tileY <= 10)
                    {
                        instancia.juego.sombra.setPosicionY(instancia.juego.sombra.getPosicionY() - 32);
                    }
                    instancia.juego.escorpion.setPosicionY(instancia.juego.escorpion.getPosicionY() - 32);
                    
                    
                    //instancia.juego.esqueleto.setPosicionY(instancia.juego.esqueleto.getPosicionY() - 1);
                }
            } else 
            {
                if(Vidas.getNumVidas()<=0)
            {
                    try {
                    try {
                        Control.Singleton().setEstadoJuego(Control.EstadosJuego.GameOver);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaLaberinto1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaLaberinto2.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
                /*Vidas.setNumVidas(Vidas.getNumVidas() - 1);
                if(Vidas.getNumVidas() < 0)
                {
                    Vidas.setNumVidas(0);
                }*/
            }

        }
        
        //Prueba sube vidas
        
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            juego.animado.dispara();
            Vidas.setNumVidas(Vidas.getNumVidas() + 1);
            if(Vidas.getNumVidas() >= 5)
            {
                Vidas.setNumVidas(5);
            }
        }
        
    }

    public int devuelveTile() {
        return nivel[tileX][tileY];
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    void adKey() throws FileNotFoundException, IOException {
        //throw new UnsupportedOperationException("Not yet implemented");
        addKeyListener(Singleton());
    }
    
    

    
    // fin prueba audio

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icono/icono2.gif")));
    }
    
}
