/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package academiafulbito.vista.utilidades;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Ronald J
 */
public class Imagen {

    public static byte[] convertirFotoJPG(File imagen,int nuevo_ancho,int nuevo_alto)
            throws SQLException, FileNotFoundException, IOException{
        if(imagen!=null){
            try{
                BufferedImage biFotoCargada = cargarImagen(imagen);
                BufferedImage biFotoRedimensioanda = redimensionaFoto(biFotoCargada, nuevo_ancho, nuevo_alto);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(biFotoRedimensioanda, "JPG", byteArrayOutputStream);
                byteArrayOutputStream.flush();
                byte[] b=byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return b;
            }catch(Exception ex){
                ex.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    //metodo utilizado para cargar la imagen del disco de la pc
    public static BufferedImage cargarImagen(File imagen){
        BufferedImage bi_foto=null;
        try{
            if(!isCMYK(imagen)){
                bi_foto=ImageIO.read(imagen);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        return bi_foto;
    }

    private static boolean isCMYK(File filename){
        boolean result = false;
        BufferedImage img = null;
        try{
            img = ImageIO.read(filename);
        }catch (IOException e){
            ;
        }
        if (img != null){
            int colorSpaceType = img.getColorModel().getColorSpace().getType();
            result = colorSpaceType == ColorSpace.TYPE_CMYK;
        }
        return result;
    }

    public static BufferedImage redimensionaFoto(BufferedImage bi_foto,int nuevo_ancho,int nuevo_alto){
        int w = bi_foto.getWidth();
        int h = bi_foto.getHeight();
        BufferedImage bufim = new BufferedImage(nuevo_ancho, nuevo_alto, bi_foto.getType());

        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bi_foto, 0, 0, nuevo_ancho, nuevo_alto, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }

    public static Image abrirImagen(byte[] foto) throws SQLException, IOException {
        Blob imagen = new SerialBlob(foto);
        return javax.imageio.ImageIO.read(imagen.getBinaryStream());
    }
}
