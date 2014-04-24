/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.event.*;

/**
 *
 * @author entornos
 */
public class PersonajeJugable extends Sprite implements KeyListener {
 
    private boolean arriba, abajo, izquierda, derecha;
    private int velocidad = 2;
    private int puntos;
    private Sprite equipado;
    //private Inventario inventario;
    
    private static int cont = 1;
    
    public PersonajeJugable(String img, int x, int y) {
        super(img, x, y);
        this.puntos = 0;
        this.getInventario().setMax(10);
        this.equipado = null;
        this.setSalud(9);
    }
 
    public int getVelocidad() {
        return this.velocidad;
    }
    
    public void setVelocidad(int vel) {
        this.velocidad = vel;
    }
    
    public int getPuntos(){
        return this.puntos;
    }
    
    public void sumaPuntos(int puntos) {
        this.puntos += puntos;
    }

    public Sprite getEquipado() {
        return equipado;
    }

    public void setEquipado(Sprite equipado) {
        this.equipado = equipado;
    }
    
    public void setEquipado(int i) {
        if(i < this.getInventario().numObjetos() && i >= 0)
            this.equipado = this.getInventario().getObjeto(i);
        else {
            this.equipado = null;
            /* System.out.println("Estás tratando de equipar desde inventario un objeto que no tienes en el inventario. \n"
             *              + "(Tratando de equipar: " + i + ". Inventario con: " + this.getInventario().numObjetos() + " objetos.");*/
        }
    }
    
    @Override
    public void tirarObjeto(Nivel nivel, int i) {
        Sprite equipado = this.getInventario().getObjeto(i);
        super.tirarObjeto(nivel, i);
        
        if( this.getEquipado() == equipado && this.getInventario().getSelected() != -1 ) {
            this.setEquipado(null);
        }
    }
    
    @Override
    public void tirarObjeto(Nivel nivel, Sprite objeto) {     
        int i = this.getInventario().getInventario().indexOf(objeto);
        
        if(i != -1) 
            this.tirarObjeto(nivel, i);
    }
    
    public void actualiza() {
        if(izquierda) {
            if (this.getX() > 0){
                int newX = this.getX() - velocidad;
                this.setX(newX);
            
                this.anima("izquierda");
            }
        }
        
        if(derecha) {
            if ((this.getX()+this.getWidth()) < 500){
                int newX = this.getX() + velocidad;
                this.setX(newX);
            
                this.anima("derecha");
            }
        }
        
        if(arriba) {
            if (this.getY() > 0){
                int newY = this.getY() - velocidad;
                this.setY(newY);
            
                this.anima("arriba");
            }
        }
        
        if(abajo) {
            if ((this.getY() + this.getHeight()) < 500){
                int newY = this.getY() + velocidad;
                this.setY(newY);
            
                this.anima("abajo");
            }
        }
    }
    
    public void anima(String direccion) {
        
        int img = 1;
        
        if(cont < 10) {
            img = 1;
        } else if(cont < 20) {
            img = 2;
        } else if(cont < 30) {
            img = 4;
        } else if(cont < 40) {
            img = 4;
        } else {
            cont = 0;
            img = 1;
        }
        
        this.setSprite("sprites/" + direccion + img + ".png");
        
        cont++;  
    }
 
    // tecla sin pulsar
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                abajo = false;
                break;
            case KeyEvent.VK_UP:
                arriba = false;
                break;
            case KeyEvent.VK_LEFT:
                izquierda = false;
                break;
            case KeyEvent.VK_RIGHT:
                derecha = false;
                break;
        }
    }
 
    //tecla presionada
    public void keyPressed(Nivel nivel, KeyEvent e) {
        
        // Equipar items
        if(e.getKeyCode() == KeyEvent.VK_1) {
            this.setEquipado(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_2) {
            this.setEquipado(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_3) {
            this.setEquipado(2);
        }
        if(e.getKeyCode() == KeyEvent.VK_4) {
            this.setEquipado(3);
        }
        if(e.getKeyCode() == KeyEvent.VK_5) {
            this.setEquipado(4);
        }
        if(e.getKeyCode() == KeyEvent.VK_6) {
            this.setEquipado(5);
        }
        if(e.getKeyCode() == KeyEvent.VK_7) {
            this.setEquipado(6);
        }
        if(e.getKeyCode() == KeyEvent.VK_8) {
            this.setEquipado(7);
        }
        if(e.getKeyCode() == KeyEvent.VK_9) {
            this.setEquipado(8);
        }
        if(e.getKeyCode() == KeyEvent.VK_0) {
            this.setEquipado(9);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_Q) {
            this.tirarObjeto(nivel, this.getEquipado());
        }
        
        // Mover personaje por la pantalla (no usado...)
        /*
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                arriba = true;
                break;
            case KeyEvent.VK_LEFT:
                izquierda = true;
                break;
            case KeyEvent.VK_RIGHT:
                derecha = true;
                break;
            case KeyEvent.VK_DOWN:
                abajo = true;
                break;
        }
        * */
    }
 
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
