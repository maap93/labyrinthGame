
package laberintodecretaalpha;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alumno
 */
public class Obstaculo {
    
     private int x;
    private int y;
    private String nombreImagen;
    private Image imagen;
    private Rectangle colisionador;
    private boolean muestraColisionador;
    
    Obstaculo(int px,int py,String ni)
    {
         try {
             x=px;
             y=py;
             nombreImagen=ni;
             imagen=BufferedImageDic.Singleton().imagen(nombreImagen);
             int ancho=imagen.getWidth(null);
             int alto=imagen.getHeight(null);
             colisionador=new Rectangle(x,y,ancho,alto);
         } catch (IOException ex) {
             Logger.getLogger(Obstaculo.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    Obstaculo(int px,int py,int ancho,int alto)
    {
       imagen=null;
       colisionador=new Rectangle(px,py,ancho,alto);
    }
    
   void dibuja(Graphics contexto)
   {
       
       if(imagen!=null) {
           contexto.drawImage(imagen,x,y,null);
       }
       if(muestraColisionador)
       {
           contexto.setColor(Color.red);
           contexto.drawRect(colisionador.x,colisionador.y, 
                             colisionador.width, colisionador.height);
       }
   }
   
   boolean colisiono(Rectangle otro)
   {
       return colisionador.intersects(otro);
   }
   
   void mostrarColisionador(boolean mostrar)
   {
       muestraColisionador=mostrar;
   }
   
   Rectangle getColisionador()
   {
       return colisionador;
   }
    
   Image getImagen()
   {
       return imagen;
   }
   
   public void setImagen(BufferedImage imagen)
   {
       this.imagen = imagen;
   }
}