package laberintodecretaalpha;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author Rodrack
 */
public class Nivel3 {
    
    Personaje heroe = null;
    //Personaje heroe2 = null;
    //Escenario escenario = null;
    Personaje animado = null;
    Enemigo esqueleto = null;
    Personaje arregloP[] = new Personaje[4];
    HUD hud = null;
    Vidas vida = null;
    VidasNegativas vidaNegativa = null;
    Cronometro tiempoPaso = new Cronometro(9);
    
    //int numVidas = vida.getVidas();
    
    public Nivel3(){
        
    }
    
    public void accion(boolean UP, boolean DWN, boolean RGHT, boolean LFT, boolean A, boolean W, boolean S, boolean D, boolean ACC){
        if(UP) {
            for(int i = 0; i < 4; i++){
                arregloP[i].setDireccion(Personaje.Direccion.atras);
            }
        }
        if(DWN) {
            for(int i = 0; i < 4; i++){
                arregloP[i].setDireccion(Personaje.Direccion.adelante);
            }
        }
        if(RGHT) {
            for(int i = 0; i < 4; i++){
                arregloP[i].setDireccion(Personaje.Direccion.derecha);
            }
        }
        if(LFT) {
            for(int i = 0; i < 4; i++){
                arregloP[i].setDireccion(Personaje.Direccion.izquierda);
            }
        }
        if(ACC) {
            for(int i = 0; i < 4; i++){
                arregloP[i].acelera();
            }
        }
        
            
    }
    
    /*public  boolean detectarColision(){
       Rectangle colHeroe = animado.getColisionador();
       Rectangle colEnemigo = esqueleto.getColisionador();
       if(colHeroe.intersects(colEnemigo)){
           System.out.println("COSIFOSJ");
           return true;
       }
       return false;
    /*   if(esqueleto.getPosicionX() >= 135 && esqueleto.getPosicionX() <= 165 && esqueleto.getPosicionY() >= 175 && esqueleto.getPosicionY() <= 225){
           System.out.println("COLISION");
           return true;
       }
       return false;
       
    }*/
    
    public void actualiza(VentanaLaberinto3 ventana) throws FileNotFoundException, IOException{
        BufferStrategy preparacion = ventana.getBufferStrategy();
        Graphics dibujar = preparacion.getDrawGraphics();
        //dibujar.drawImage(escenario.getImagen(), escenario.getXi(), escenario.getYi(), escenario.getXf(), escenario.getYf(), ventana);
        //dibujar.drawImage(heroe.getImagen(), heroe.getPosicionX(), heroe.getPosicionY(), ventana);
       //dibujar.drawImage(heroe2.getImagen(), heroe2.getPosicionX(), heroe2.getPosicionY(), ventana);
         dibujar.setColor(Color.BLACK);
        
        
        ventana.desplazaFondo(dibujar);
        ventana.pintaTiles(dibujar);
        
        
        dibujar.drawImage(hud.getImagen(), hud.getPosX(), hud.getPosY(), ventana);
        
        
        for(int i=0; i < Vidas.numVidas; i++)
        {
            if(i == 0)
            {
                dibujar.drawImage(vida.getImagen(),vida.getPosX() + (i * 38), vida.getPosY(), ventana);
            }
            
            if(i == 1)
            {
                dibujar.drawImage(vida.getImagen(),vida.getPosX() + (i * 38), vida.getPosY(), ventana);
            }
            
            if(i == 2)
            {
                dibujar.drawImage(vida.getImagen(),vida.getPosX() + (i * 38), vida.getPosY(), ventana);
            }
            
            if(i == 3)
            {
                dibujar.drawImage(vida.getImagen(),vida.getPosX() + (i * 38), vida.getPosY(), ventana);
            }
            
            if(i == 4)
            {
                dibujar.drawImage(vida.getImagen(),vida.getPosX() + (i * 38), vida.getPosY(), ventana);
            }
                      
        }
        
        
        //Prueba Vidas Negativas
        
        
        
            if(Vidas.getNumVidas() == 0)
            {
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (0 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (1 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (2 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (3 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (4 * 38), vidaNegativa.getPosY(), ventana);
            }
            
            if(Vidas.getNumVidas() == 1)
            {
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (1 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (2 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (3 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (4 * 38), vidaNegativa.getPosY(), ventana);
            }
            
            if(Vidas.getNumVidas() == 2)
            {
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (2 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (3 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (4 * 38), vidaNegativa.getPosY(), ventana);
            }
            
            if(Vidas.getNumVidas() == 3)
            {
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (3 * 38), vidaNegativa.getPosY(), ventana);
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (4 * 38), vidaNegativa.getPosY(), ventana);
            }
            
            if(Vidas.getNumVidas()== 4)
            {
                dibujar.drawImage(vidaNegativa.getImagen(),vidaNegativa.getPosX() + (4 * 38), vidaNegativa.getPosY(), ventana);
            }
            
        //    esqueleto.patrulla(150, 10);
        
        
        //Fin Prueba de Vidas Negativas
           
        
        
        
        //animado.avanza();
        dibujar.drawImage(animado.getImagen(), animado.getPosicionX(), animado.getPosicionY(), ventana);
        animado.cambiaDireccion(ventana.getWidth(), ventana.getHeight());
        //dibujar.drawImage(esqueleto.getImagen(), esqueleto.getPosicionX(), esqueleto.getPosicionY(), ventana);
        //esqueleto.patrulla(120, 0);
      
 
        
        preparacion.show(); 
    }
    
    
 
    
    public void setPersonaje(String nombreImagen, int sprites)
    {
        this.animado = new Personaje(200,150,nombreImagen, sprites);
    }
    
    public void setEnemigo(String nombreImagen, int sprites){
        this.esqueleto = new Enemigo(290,150,nombreImagen, sprites);
    }
    
    public void setHud(String nombre)
    {
        this.hud = new HUD(nombre);
    }
    
    
    public void setVidas(String nombre, int numVidas)
    {
        this.vida = new Vidas(nombre,numVidas);
    }
    
    public void setVidasNegativas(String nombre)
    {
        this.vidaNegativa = new VidasNegativas(nombre);
    }
    
    
}