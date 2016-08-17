/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package laberintodecretaalpha;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrack
 */
public class Enemigo {
    
    private int x;
    private int y;
    private String nombreImagen;
    private BufferedImage imagen;
    private Rectangle colisionador;
    private boolean muestraColisionador;
    private int ancho;
    private int alto;
    
    Enemigo(int px,int py,String ni, int numSprites)
    {
        try {
            x=px;
            y=py;
            nombreImagen=ni;
            imagen=BufferedImageDic.Singleton().imagen(nombreImagen);
            ancho = imagen.getWidth();
            alto = imagen.getHeight();
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
    
    
    Enemigo(String ni, int numSprites)
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
   
   public enum Direccion {adelante, izquierda, derecha, atras}
    private int paso, velocidad, sprites, posx, posy;
    private int avance = 32;
    private Direccion direccion;
    private Cronometro tiempoAvance, tiempoPaso;
   
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
                    break;
                case izquierda:
                    this.setPosicionX(getPosicionX() - velocidad);
                    break;
                case adelante:
                    this.setPosicionY(getPosicionY() + velocidad);
                    break;
                case atras:
                    this.setPosicionY(getPosicionY() - velocidad);
                    break;
            }
        }
    }
    
    
    
    public void patrullaY(int max, int min){
        if(tiempoAvance.esTiempo()){
            this.setPosicionY(getPosicionY() + avance);
            if(getPosicionY() + avance >= max){
                avance *= -1;
                direccion = Direccion.atras;
            }
            if(getPosicionY() + avance <= min){
                avance *= -1;
                direccion = Direccion.adelante;
            }
        }
    }
    
    
    public void patrullaX(int max, int min){
        if(tiempoAvance.esTiempo()){
            this.setPosicionX(getPosicionX() + avance);
            if(getPosicionX() + avance >= max){
                avance *= -1;
                direccion = Direccion.izquierda;
            }
            if(getPosicionX() + avance <= min){
                avance *= -1;
                direccion = Direccion.derecha;
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
                    ima = imagen.getSubimage( (paso*32) , 0, 32, 32); 
                    break;                    
                case 1:
                    ima = imagen.getSubimage( (paso*32) , 32, 32, 32);
                    break;                    
                case 2:
                    ima = imagen.getSubimage( (paso*32) , 64, 32, 32);
                    break;                    
                case 3:
                    ima = imagen.getSubimage( (paso*32) , 96, 32, 32);
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
