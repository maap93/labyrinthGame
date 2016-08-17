/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintodecretaalpha;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author PoloGarcia
 */
public class ImageIconDic extends HashMap<String, ImageIcon> {

    private static ImageIconDic instancia = null;

    public static ImageIconDic singleton() {
        if (instancia == null) {
            instancia = new ImageIconDic();
            cargaImagenes();
        }
        return instancia;
    }

    public static void cargaImagenes() {
        String fuente = "icons";
        File carpetaGifs = new File(fuente);
        String[] nombres = carpetaGifs.list();
        for (int i = 0; i < nombres.length; i++) {
            if (nombres[i].endsWith("gif") || nombres[i].endsWith("png") || nombres[i].endsWith("jpg")) {
                String llave = nombres[i].substring(0, nombres[i].length() - 4);
                ImageIcon imagen = new ImageIcon(fuente + "/" + nombres[i]);
                singleton().put(llave, imagen);
                //System.out.println("Procesando:" + llave + " " + 100 * i / nombres.length + "%");
            }
        }
    }

    public Image imagen(String llave) {
        return singleton().get(llave).getImage();
    }
    
    public ImageIcon getImageIcon(String llave){
        return singleton().get(llave);
    }
}

