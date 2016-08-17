/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author M!ke
 */
public class AudioSystem{
    
    
        public static AudioSystem instancia = null;
        
        public static AudioSystem Singleton()
        {
            if(instancia == null)
            {
                instancia = new AudioSystem();
            }
            return instancia;
        }
    
        //Audio Tema Principal
        public static String temaPrincipal = "Audio/TemaPrincipal.wav";
        //Audio Nivel 1
        public static String laberinto1 = "Audio/laberinto.wav";
        //Audio Nivel 2
        public static String laberinto2 = "Audio/laberinto2.wav";
        //Audio Nivel 3
        public static String laberinto3 = "Audio/laberinto3.wav";
        //Audio Ventana GameOver
        public static String gameover = "Audio/GameOver2.wav";
        //Audio Ventana Ganar
        public static String ganar = "Audio/Victory2.wav";
        //Audio Pausa
        public static String pausa = "Audio/audioPausa.wav";
        //Audio Progreso
        public static String progreso = "Audio/Progreso.wav";
        
        public static InputStream in;
        public static AudioStream audioStream;
        public static ContinuousAudioDataStream loop = null;
          
        
        //Reproducir musica
        public void musicPlay() throws FileNotFoundException, IOException
        {
            AudioPlayer.player.start(audioStream);
        }
        
        //Detener musica
        public void musicStop() throws FileNotFoundException, IOException
        {
            AudioPlayer.player.stop(audioStream);
        }
}
