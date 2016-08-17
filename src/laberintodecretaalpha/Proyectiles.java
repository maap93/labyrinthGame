/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M!ke
 */
public class Proyectiles {

    
    
    // Variables de instancia
    
    private int posX;
    private int posY;
    private String nombreImagen;
    private BufferedImage imagen;
    private boolean visible = true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
    //Constructor
    
    public Proyectiles(int startX, int startY)
    {
        try {
            posX = startX;
            posY = startY;
            nombreImagen = "flecha";
            imagen=BufferedImageDic.Singleton().imagen(nombreImagen);
            visible = true;
        } catch (IOException ex) {
            Logger.getLogger(Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
    
    public void moveX()
    {
        posX = posX + 2;
        if(posX > 400)
        {
            visible = false;
        }
    }
    
    public void moveY()
    {
        posY = posY + 2;
        if(posY > 300)
        {
            visible = false;
        }
    }
}
