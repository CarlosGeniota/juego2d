/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author entornos
 */
public class Sprite {
    private Image imagen;
    private int x, y;
    private String nombre;
    private Inventario inventario;
    private int relX, relY;
    private boolean visible;
    private boolean solido;
    private boolean comible;
    private boolean cogible;
    private boolean contenedor;
    private boolean conversable;
    // private boolean legible;
    private int puntos;
    private int salud;

    
    // CONSTRUCTORES
    public Sprite() {
        // CONSTRUCTOR VACIO
        this.reset();
    }
    public Sprite(String img, int x, int y) {
        
        this.reset();
        
        this.x = x;
        this.y = y;
        this.relX = x;
        this.relY = y;
        
        this.setSprite(img);
    }
    
    public Sprite(String img, int x, int y, String nombre) {
        
        this.reset();
        
        this.x = x;
        this.y = y;
        this.relX = x;
        this.relY = y;
        
        this.nombre = nombre;
        
        this.setSprite(img);
    }
    
    public Sprite(String img, int x, int y, boolean visible) {
        
        this.reset();
        
        this.x = x;
        this.y = y;
        this.relX = x;
        this.relY = y;
        
        this.visible = visible;
        
        this.setSprite(img);
    }
    
    public Sprite(String img, int x, int y, boolean visible, String nombre) {
        
        this.reset();
        
        this.x = x;
        this.y = y;
        this.relX = x;
        this.relY = y;
        
        this.visible = visible;
        this.nombre = nombre;
        
        this.setSprite(img);
    }

    
    // GETTERS Y SETTERS
    
    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
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
    
    public int getRelX() {
        return relX;
    }

    public void setRelX(int x) {
        this.relX = x;
    }

    public int getRelY() {
        return relY;
    }

    public void setRelY(int y) {
        this.relY = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isSolido() {
        return solido;
    }

    public void setSolido(boolean solido) {
        this.solido = solido;
    }

    public boolean isComible() {
        return comible;
    }

    public void setComible(boolean comible) {
        this.comible = comible;
    }

    public boolean isCogible() {
        return cogible;
    }

    public void setCogible(boolean cogible) {
        this.cogible = cogible;
    }

    public boolean isConversable() {
        return conversable;
    }

    public void setConversable(boolean conversable) {
        this.conversable = conversable;
    }
    
    
    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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

    public boolean isContenedor() {
        return contenedor;
    }

    public void setContenedor(boolean contenedor) {
        this.contenedor = contenedor;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }
    
    public void addSalud(int salud) {
        if(this.salud+salud >= 0)
            this.salud += salud;
        else
            this.salud = 0;
    }
    
    
    // FUNCIONES DE CLASE
    
    public int getWidth() {
        return this.imagen.getWidth(null);
    }
    
    public int getHeight() {
        return this.imagen.getHeight(null);
    }
    
    public void setSprite(String img) {
        if(img.equals("")) {
            System.out.println("El sprite no tiene imagen. No se cargará una por defecto.");
        } else {
            ImageIcon imagen_ic = new ImageIcon(this.getClass().getResource(img));
            this.imagen = imagen_ic.getImage();
        }
    }
    
    public void putSprite(Graphics grafico) { 
        if (this.visible) 
            grafico.drawImage(this.imagen, this.x, this.y, null);
    }
    
    public void putSprite(Graphics grafico, int coordenadaHorizontal, int coordenadaVertical) { 
        this.x = coordenadaHorizontal;
        this.y = coordenadaVertical;
        if (this.visible) 
            grafico.drawImage(this.imagen, this.x, this.y, null);
    }
    
    private void reset() {
        this.nombre = "Objeto sin nombre.";
        this.solido = false;
        this.visible = true;
        this.comible = false;
        this.cogible = false;
        this.puntos = 0;
        this.contenedor = false;
        this.inventario = new Inventario();
    }
    
    
    public void tirarObjeto(Nivel nivel, int i) {
        if(i < this.getInventario().numObjetos() && i >= 0) {
                int newX = -nivel.getX() + this.getX() + this.getImagen().getWidth(null) + 20;
                int newY = -nivel.getY() + this.getY() + this.getImagen().getHeight(null);
                this.getInventario().getObjeto(i).setX(newX);
                this.getInventario().getObjeto(i).setY(newY);
                this.getInventario().getObjeto(i).setRelX(newX);
                this.getInventario().getObjeto(i).setRelY(newY);
                this.getInventario().getObjeto(i).setVisible(true);
                
                nivel.getInventario().addObjeto(this.getInventario().getObjeto(i));
                this.getInventario().quitaObjeto(i); 
                // System.out.println("veamos donde va... X:"+newX+", Y:"+newY);
                // System.out.println("El inventario del nivel tiene " + nivel.getInventario().numObjetos() + " / " + nivel.getInventario().getMax() + " objetos");
            }
    }
    
    public void tirarObjeto(Nivel nivel, Sprite objeto) {
        if(this.getInventario().getInventario().contains(objeto)) {
            
                int i = this.getInventario().getInventario().indexOf(objeto);
            
                this.tirarObjeto(nivel, i);
                
                this.getInventario().quitaObjeto(i);
            }
    }
    
}
