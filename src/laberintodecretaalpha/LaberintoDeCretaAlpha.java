/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import laberintodecretaalpha.Control.EstadosJuego;

/**
 *
 * @author M!ke
 */
public class LaberintoDeCretaAlpha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        //BufferedImageDic.Singleton();
        //ImageIconDic.singleton();
        Control.Singleton().setEstadoJuego(EstadosJuego.Menu);
        //VentanaConfiguracion.Singleton().setVisible(true);
        VentanaMenuPrincipal.Singleton().setVisible(true);
    
        while (true) 
        {
            Control c = Control.Singleton();
            Control.EstadosJuego e = c.getEstadojuego();
            System.out.print(""); //Sin esta linea no funciona el repaint

            switch (e) 
            {
                case Nivel1:
                    VentanaLaberinto1.Singleton().repaint();
                    break;

                case Nivel2:
                    VentanaLaberinto2.Singleton().repaint();
                    break;
                    
                case Nivel3:
                    VentanaLaberinto3.Singleton().repaint();
                    break;
                    
                case GameOver:
                    for(int i = 0; i < 3 ;i++)
                    {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    VentanaGameOver.labelContinuar.setVisible(true);
                    VentanaGameOver.labelContinuar1.setVisible(true);
                    break;
                    
                case Ganar:
                    for(int i=0;i<5;i++)
                    {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    VentanaGanar.labelContinuar.setVisible(true);
                    VentanaGanar.labelContinuar1.setVisible(true);
                    break;
                            

                case Cargando:
                    for (int i = 0; i <= 100; i++) 
                    {
                        
                        VentanaProgreso.progressBar.setStringPainted(true);
                        VentanaProgreso.progressBar.setValue(i);        
                        
                        if (i == 10 || i == 30 || i == 50 || i == 70 || i == 90)
                        {
                            TimeUnit.SECONDS.sleep(1);
                        }
                        
                        if (i == 100) 
                        {
                            if(Control.Singleton().estadoPasado == Control.EstadosJuego.Seleccion)
                            {
                                Control.Singleton().setEstadoJuego(EstadosJuego.Nivel1);
                                VentanaProgreso.Singleton().setVisible(false);
                            }
                            if(Control.Singleton().estadoPasado == Control.EstadosJuego.Nivel1)
                            {
                                Control.Singleton().setEstadoJuego(EstadosJuego.Nivel2);
                                VentanaProgreso.Singleton().setVisible(false);
                            }
                            if(Control.Singleton().estadoPasado == Control.EstadosJuego.Nivel2)
                            {
                                Control.Singleton().setEstadoJuego(EstadosJuego.Nivel3);
                                VentanaProgreso.Singleton().setVisible(false);
                            }
                        }
                    }
                    break;

            }
        }
        
        
        
    }
}
