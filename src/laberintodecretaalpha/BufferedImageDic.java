package laberintodecretaalpha;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class BufferedImageDic extends HashMap<String, BufferedImage> {
    
    static private BufferedImageDic instancia=null;
    
    
    
    public static BufferedImageDic Singleton() throws IOException
    {
        if (instancia==null)
        {      
            instancia= new BufferedImageDic();
            cargaImagenes();
        }
        
        return instancia;
    }
    
     public static void cargaImagenes() throws IOException
    {
        String fuente="imagenes";
        File carpetaGifs = new File(fuente);
        String [] nombres = carpetaGifs.list();
        for(int i=0;i<nombres.length;i++)
        {
            String llave=nombres[i].substring(0, nombres[i].length()-4);
            //System.out.println(llave);
            BufferedImage image = ImageIO.read(new File(fuente+"/"+nombres[i]));
            
            Singleton().put(llave, image);
            //System.out.println("Procesando:"+llave+" "+100*i/nombres.length+"%");
        }
    }
    
    public BufferedImage imagen(String llave) throws IOException
    {
        return Singleton().get(llave);
    }
    
}