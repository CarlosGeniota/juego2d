/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */
public class Nivel {
    private Inventario inventario;
    private Image background;
    private ArrayList<Conversacion> conversaciones;
    private int x = 0, y = 0;
    private boolean bgArriba, bgAbajo, bgIzquierda, bgDerecha;
    private boolean activo;
    
    public Nivel(String fondo) {
        //
        ImageIcon background_ic = new ImageIcon(this.getClass().getResource(fondo));
        this.background = background_ic.getImage();
        this.inventario = new Inventario();
        this.conversaciones = new ArrayList<>();
        
        this.activo = true;
    }
    
    public Nivel(String fondo, Sprite[] sprite) {
        // Meter todas las mierdas en el constructor
        ImageIcon background_ic = new ImageIcon(this.getClass().getResource(fondo));
        this.background = background_ic.getImage();
        this.inventario = new Inventario(sprite);
        this.conversaciones = new ArrayList<>();
        
        this.activo = true;
    }

    public Sprite getSprite(int i) {
        return inventario.getObjeto(i);
    }
    
    public void setSprite(Sprite sprite){
        inventario.addObjeto(sprite);
    }

    public void setInventario(Sprite[] sprite) {
        this.inventario.setInventario(sprite);
    }
    
    public void setIventario(Inventario inventario) {
        this.inventario = inventario;
    }
    
    public Inventario getInventario() {
        return this.inventario;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public ArrayList<Conversacion> getConversaciones() {
        return conversaciones;
    }
    
    public Conversacion getConversacion(int i) {
        return this.conversaciones.get(i);
    }

    public void setConversaciones(ArrayList<Conversacion> conversacion) {
        this.conversaciones = conversacion;
    }
    
    public void setConversacion(int i, Conversacion conversacion) {
        this.conversaciones.set(i, conversacion);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBgArriba() {
        return bgArriba;
    }

    public void setBgArriba(boolean bgArriba) {
        this.bgArriba = bgArriba;
    }

    public boolean isBgAbajo() {
        return bgAbajo;
    }

    public void setBgAbajo(boolean bgAbajo) {
        this.bgAbajo = bgAbajo;
    }

    public boolean isBgIzquierda() {
        return bgIzquierda;
    }

    public void setBgIzquierda(boolean bgIzquierda) {
        this.bgIzquierda = bgIzquierda;
    }

    public boolean isBgDerecha() {
        return bgDerecha;
    }

    public void setBgDerecha(boolean bgDerecha) {
        this.bgDerecha = bgDerecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
    public void addNivel(Graphics grafico) { 
        if (this.activo) {
            // Pintamos el fondo
            grafico.drawImage(this.background, this.x, this.y, null);
            
            // Pintamos los sprites del nivel
            for(int i=0; i<this.inventario.numObjetos(); i++) {
                if(this.inventario.getObjeto(i).isVisible()) {
                    grafico.drawImage(this.inventario.getObjeto(i).getImagen(), this.inventario.getObjeto(i).getX(), this.inventario.getObjeto(i).getY(), null);
                }
            }
        }
    }
    
    public void actualiza() {
        
        for(int i=0; i<this.inventario.numObjetos(); i++) {
            int newX = this.inventario.getObjeto(i).getRelX() + this.x;
            int newY = this.inventario.getObjeto(i).getRelY() + this.y;
            this.inventario.getObjeto(i).setX(newX);
            this.inventario.getObjeto(i).setY(newY);
        }
    }
    
}
