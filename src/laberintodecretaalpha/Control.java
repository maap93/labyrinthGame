/*44
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import sun.audio.AudioStream;

/**
 *
 * @author M!ke
 */
public class Control implements Serializable  {
    
    static Control instancia = null;

    boolean refrescar() {
        return System.currentTimeMillis() % 32 == 0;
    }
    
    public enum EstadosJuego{Menu, Seleccion, Pausa, Nivel1, Nivel2, Nivel3, GameOver,Ganar,Cargando, Configuracion};
    EstadosJuego estadojuego = EstadosJuego.Menu;
    long startTime = 0;
    long endTime = 0;
    
    

    
    public  EstadosJuego estadoPasado = EstadosJuego.Menu;

    public EstadosJuego getEstadoPasado() {
        return estadoPasado;
    }

    public void setEstadoPasado(EstadosJuego estadoPasado) {
        this.estadoPasado = estadoPasado;
    }
    
    
    static Control Singleton()
    {
        if(instancia==null)
        {
            instancia = new Control();
        }
        return instancia;
    }
    

    
    
    //Setters and Getters
    public EstadosJuego getEstadojuego() {
        return estadojuego;
    }

    public void setEstadoJuego(EstadosJuego Nuevoestadojuego) throws IOException, InterruptedException 
    {
        System.out.println("NuevoEstadoJuego " + Nuevoestadojuego);
        if(estadojuego != Nuevoestadojuego)
        {estadoPasado = estadojuego;}
        System.out.println("EstadoPasado "+estadoPasado);
        
        switch(Nuevoestadojuego)
        {
            case Menu:
                AudioSystem.Singleton().musicStop();
                
                if(VentanaGameOver.gameOverActivado)
                {
                    VentanaLaberinto1.Singleton().clear();//Reinicia la instancia del nivel1
                    VentanaLaberinto2.Singleton().clear();//Reinicia la instancia del nivel2
                    VentanaLaberinto3.Singleton().clear();//Reinicia la instancia del nivel3
                
                    VentanaLaberinto1.nivelCargado = false;
                    VentanaLaberinto2.nivelCargado = false;
                    VentanaLaberinto3.nivelCargado = false;
                    
                    VentanaGameOver.gameOverActivado = false;
                }
                
                if(VentanaConfiguracion.prenderMusica)
                {
                    AudioSystem.in = new FileInputStream(AudioSystem.temaPrincipal);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();
                }
                VentanaMenuPrincipal.Singleton().setVisible(true);
                break;
                
            case Seleccion:
                VentanaSeleccionPersonaje.Singleton().setVisible(true);
                break;
                
            case Pausa:
                AudioSystem.Singleton().musicStop();
                if(VentanaConfiguracion.prenderMusica)
                {
                    AudioSystem.in = new FileInputStream(AudioSystem.pausa);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();
                }
                VentanaPausa.Singleton().setVisible(true);
                break;
      
            case Nivel1:
                //Audio
                AudioSystem.Singleton().musicStop();
                if(VentanaConfiguracion.prenderMusica)
                {
                    AudioSystem.in = new FileInputStream(AudioSystem.laberinto1);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();               
                }
                if(VentanaLaberinto1.nivelCargado == false)
                {
                    startTime = System.currentTimeMillis();
                    VentanaLaberinto1.Singleton().cargaNivel("nivel.def");
                    VentanaLaberinto1.Singleton().adKey();
                }
                VentanaLaberinto1.Singleton().setVisible(true);
                break;
                
            case Nivel2:
                //Audio
                AudioSystem.Singleton().musicStop();
                if(VentanaConfiguracion.prenderMusica)
                {
                    AudioSystem.in = new FileInputStream(AudioSystem.laberinto2);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();
                }
                if(VentanaLaberinto2.nivelCargado == false)
                {
                    VentanaLaberinto2.Singleton().cargaNivel("nivel2.def");
                    VentanaLaberinto2.Singleton().adKey();
                }
                VentanaLaberinto2.Singleton().setVisible(true);
                break;
                
            case Nivel3:
                //Audio
                AudioSystem.Singleton().musicStop();
                if(VentanaConfiguracion.prenderMusica)
                {
                    AudioSystem.in = new FileInputStream(AudioSystem.laberinto3);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();
                }
                if(VentanaLaberinto3.nivelCargado == false)
                {
                    endTime = System.currentTimeMillis();
                    VentanaLaberinto3.Singleton().cargaNivel("nivel3.def");
                    VentanaLaberinto3.Singleton().adKey();
                }
                VentanaLaberinto3.Singleton().setVisible(true);
                break;
                
            case GameOver:
                //Audio
                AudioSystem.Singleton().musicStop();
                if(VentanaConfiguracion.prenderMusica)
                {
                    endTime = System.currentTimeMillis();
                    AudioSystem.in = new FileInputStream(AudioSystem.gameover);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();
                }
                long totalTime = endTime - startTime;
                System.out.println("Tiempo inicio: " + startTime);
                System.out.println("Tiempo final: " + endTime);
                System.out.println("Tiempo transcurrido: " + (int) totalTime/1000d);
                
               
                
             
                
                VentanaGameOver.Singleton().setVisible(true);
                break;
                
            case Ganar:
                //Audio
                AudioSystem.Singleton().musicStop();
                if(VentanaConfiguracion.prenderMusica)
                {
                    AudioSystem.in = new FileInputStream(AudioSystem.ganar);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();
                }
                VentanaGanar.Singleton().setVisible(true);
                break;
                
            case Cargando:
                //Audio
                AudioSystem.Singleton().musicStop();
                if(VentanaConfiguracion.prenderMusica)
                {
                    AudioSystem.in = new FileInputStream(AudioSystem.progreso);
                    AudioSystem.audioStream = new AudioStream(AudioSystem.in);
                    AudioSystem.Singleton().musicPlay();
                }
                VentanaProgreso.Singleton().setVisible(true);                             
                break;
                
            case Configuracion:
                
                VentanaConfiguracion.Singleton().setVisible(true);
                break;
        }
        this.estadojuego = Nuevoestadojuego;
    }
    
    
}
