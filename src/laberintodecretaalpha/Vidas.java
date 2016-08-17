/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M!ke
 */
public class Vidas {

    
    
    // Variables de Instancia
    
    private int posX;
    private int posY;
    private BufferedImage imagen;
    private String nombreImagen;
    public static int numVidas;

    
    
    
    public Vidas(String nombre)
    {
        try{
        posX = 15;
        posY = 31;
        nombreImagen = nombre;
        imagen = BufferedImageDic.Singleton().imagen(nombreImagen);
        numVidas = 5;
        }
        catch (IOException ex) 
            {
                Logger.getLogger(Vidas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    //Prueba Vidas
    public Vidas(String nombre, int numeroDeVidas)
    {
        try{
        posX = 15;
        posY = 31;
        nombreImagen = nombre;
        imagen = BufferedImageDic.Singleton().imagen(nombreImagen);
        numVidas = Vidas.getNumVidas();
        }
        catch (IOException ex) 
            {
                Logger.getLogger(Vidas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    //Fin Prueba Vidas
    
    
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }

    public static int getNumVidas() {
        return numVidas;
    }

    public static void setNumVidas(int numVidas) {
        Vidas.numVidas = numVidas;
    }
    
}
