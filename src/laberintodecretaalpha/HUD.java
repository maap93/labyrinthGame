/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M!ke
 */
public class HUD {

    
    
    private int posX;
    private int posY;
    private BufferedImage imagen;
    private String nombreImagen;
    
    
    
    
    public HUD(String nombre)
    {
        try {
            posX = 0;
            posY = 25;
            nombreImagen = nombre;
            imagen = BufferedImageDic.Singleton().imagen(nombreImagen);
            
        } catch (IOException ex) 
            {
                Logger.getLogger(HUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
   
    
   Image getImagen()
   {
       return imagen;
   }
   
   public void setImagen(BufferedImage imagen)
   {
       this.imagen = imagen;
   }
    
   
   
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

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
    
    
}
