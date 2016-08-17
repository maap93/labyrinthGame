/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

/**
 *
 * @author M!ke
 */
public class Cronometro {
    
    private long retardo;
    private long arranque;
    
    public Cronometro(int frames){
        retardo = (1000/30)*frames;
        arranque = System.currentTimeMillis();
    }
    
    public boolean esTiempo(){
        long ahora = System.currentTimeMillis();
        if((ahora - arranque) >= retardo){
            arranque = ahora;
            return true;
        }
        
        return false;
    }
    
}
