/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.event.KeyEvent;

/**
 *
 * @author Carlos
 */
public class Movimiento {
    
    private static int cont = 1;
    
    /* Si queremos que el personaje "ande" (animacion) aunque colisione con un
     * objeto, hay que llamar a pj.anima() en keyPressed() en vez de en mover()
     */
    
    public static void keyPressed(Nivel nivel, PersonajeJugable pj, KeyEvent e) {
        
        // Shift (correr)
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            pj.setVelocidad(3);
        }
        
        // Derecha (pressed)
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            nivel.setBgDerecha(true);
        } 
        //Izquierda (pressed)
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            nivel.setBgIzquierda(true);
        } 
        // Arriba (pressed)
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            nivel.setBgArriba(true);
        } 
        // Abajo (pressed)
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            nivel.setBgAbajo(true);
        }
    }
    
    public static void keyReleased(Nivel nivel, PersonajeJugable pj, KeyEvent e) {
        
        // Shift (correr)
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            pj.setVelocidad(2);
        }
        
        // Derecha (Released)
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            nivel.setBgDerecha(false);
            pj.setSprite("sprites/derecha1.png");
        } 
        // Izquierda (Released)
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            nivel.setBgIzquierda(false);
            pj.setSprite("sprites/izquierda1.png");
        } 
        // Arriba (Released)
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            nivel.setBgArriba(false);
            pj.setSprite("sprites/arriba1.png");
        } 
        // Abajo (Released)
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            nivel.setBgAbajo(false);   
            pj.setSprite("sprites/abajo1.png");
        }
    }
    
    public static void mueveFondo(Nivel nivel, PersonajeJugable pj) {
        
        if(nivel.isBgDerecha() && nivel.getX() > -(nivel.getBackground().getWidth(null)-265) && !Colisiones.compruebaColision(nivel, pj, 3)) {
            nivel.setX(nivel.getX() - pj.getVelocidad());
            pj.anima("derecha");
        } else
        
        if(nivel.isBgIzquierda() && nivel.getX() < 236 && !Colisiones.compruebaColision(nivel, pj, 1)) {
            nivel.setX(nivel.getX() + pj.getVelocidad());
            pj.anima("izquierda");
        } else
        
        if(nivel.isBgArriba() && nivel.getY() < 220 + pj.getHeight() && !Colisiones.compruebaColision(nivel, pj, 2)) {
            nivel.setY(nivel.getY() + pj.getVelocidad());
            pj.anima("arriba");
        } else
        
        if(nivel.isBgAbajo() && nivel.getY() > -(nivel.getBackground().getHeight(null)-286) && !Colisiones.compruebaColision(nivel, pj, 4)) {
            nivel.setY(nivel.getY() - pj.getVelocidad());
            pj.anima("abajo");
        }      
    }
    
    
}
