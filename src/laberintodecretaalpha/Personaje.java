/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alumno
 */
public class Personaje {
    
    public static int x;
    public static int y;
    private String nombreImagen;
    private BufferedImage imagen;
    private Rectangle colisionador;
    private boolean muestraColisionador;
    private int ancho;
    private int alto;
    
    
    public enum Direccion {adelante, izquierda, derecha, atras}
    public static int paso,  sprites, posx, posy;
    public static int velocidad;
    
    public int getVelocidad() {
        return velocidad;
    }
    private Direccion direccion;
    private Cronometro tiempoAvance, tiempoPaso;
    
    
    static ArrayList proyectiles;

    public static ArrayList getProyectiles() {
        return proyectiles;
    }

    public static void setProyectiles(ArrayList proyectiles) {
        Personaje.proyectiles = proyectiles;
    }
    
    Personaje(int px,int py,String ni, int numSprites)
    {
        try {
            x=px;
            y=py;
            nombreImagen=ni;
            imagen=BufferedImageDic.Singleton().imagen(nombreImagen);
            ancho=imagen.getWidth(null);
            alto=imagen.getHeight(null);
            colisionador=new Rectangle(x,y,ancho,alto);
            this.sprites = numSprites;
        this.paso = 0;
        this.velocidad = 1;
        this.direccion = Direccion.adelante;
        this.tiempoPaso = new Cronometro(8);
        this.tiempoAvance = new Cronometro(4);
        proyectiles = new ArrayList();
        } catch (IOException ex) {
            Logger.getLogger(Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    Personaje(String ni, int numSprites)
    {
        try {
            x=500;
            y=500;
            nombreImagen=ni;
            imagen=BufferedImageDic.Singleton().imagen(nombreImagen);
            ancho=imagen.getWidth(null);
            alto=imagen.getHeight(null);
            colisionador=new Rectangle(x,y,ancho,alto);
            this.sprites = numSprites;
            this.paso = 0;
            this.velocidad = 1;
            this.direccion = Direccion.adelante;
            this.tiempoPaso = new Cronometro(8);
            this.tiempoAvance = new Cronometro(4);
        } catch (IOException ex) {
            Logger.getLogger(Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Intento de lanzar proyectiles
    public void dispara()
    {
        Proyectiles p = new Proyectiles(150, 200);
        proyectiles.add(p);
        
    }
    // Fin intento de proyectiles
    
    
   void actualiza(Graphics contexto)
   {
       contexto.drawImage(imagen,x,y,null);
       colisionador=new Rectangle(x,y,ancho,alto);
       if(muestraColisionador)
       {
           contexto.setColor(Color.red);
           contexto.drawRect(colisionador.x,colisionador.y, 
                             colisionador.width, colisionador.height);
       }
   }
    public void desplaza(int dx,int dy) {
        x+=dx;
        y+=dy;
        //System.out.println(x);
        //System.out.println(y);
    }
   
   boolean colisiono(Rectangle otro)
   {
       return colisionador.intersects(otro);
   }
   
   
   void mostrarColisionadores(boolean mostrar)
   {
       muestraColisionador=mostrar;
   }
   
   Rectangle getColisionador()
   {
       return colisionador;
   }
   
   // Intento de personaje animado 1 
   
   
   
    public Direccion getDireccion()
    {
        return direccion;
    }
    
    
   public void setDireccion(Direccion direccion){
        this.direccion = direccion;
        paso = 0;
        //velocidad = 1;
    }
    
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    
    public void avanza()
    {
        if(tiempoAvance.esTiempo())
        {
            switch(direccion)
            {
                case derecha:
                    this.setPosicionX(getPosicionX() + velocidad);
                    System.out.println(velocidad);
                    break;
                case izquierda:
                    this.setPosicionX(getPosicionX() - velocidad);
                    System.out.println(velocidad);
                    break;
                case adelante:
                    this.setPosicionY(getPosicionY() + velocidad);
                    System.out.println(velocidad);
                    break;
                case atras:
                    this.setPosicionY(getPosicionY() - velocidad);
                    System.out.println(velocidad);
                    break;
            }
        }
    }
    
    
    public void acelera(){
        this.setVelocidad(velocidad + 1);
    }
    
    public void cambiaDireccion(int xmax, int ymax){
        if(this.getPosicionX() > xmax){
            direccion = Direccion.izquierda;
        }else if(this.getPosicionY() > ymax){
            direccion = Direccion.atras;
        }else if(this.getPosicionX() < 0){
            direccion = Direccion.derecha;
        }else if(this.getPosicionY() < 0){
            direccion = Direccion.adelante;
        }
    }
    
    
    public int getPosicionX() {
        return x;
    }
    public int getPosicionY() {
        return y;
    }
    
    
    public void setPosicionX(int x) {
        this.x = x;
    }
    public void setPosicionY(int y) {
        this.y = y;
    }
    
   
    
   
    public BufferedImage getImagen()
    {
        BufferedImage ima = null;
        
        //paso = 1;
        
           switch (direccion.ordinal()) {
               
               case 0:
                    ima = imagen.getSubimage( (paso*24) , 0, 23, 32); 
                    break;                    
                case 1:
                    ima = imagen.getSubimage( (paso*24) , 32, 23, 32);
                    break;                    
                case 2:
                    ima = imagen.getSubimage( (paso*24) , 64, 23, 32);
                    break;                    
                case 3:
                    ima = imagen.getSubimage( (paso*24) , 96, 23, 32);
                    break;
            }
        
        if(tiempoPaso.esTiempo())
        {
            paso = (paso + 1) % 3;
        }
           
        //System.out.println();
        return ima;  
    }
   
   
   // Fin del intento
   
    
}