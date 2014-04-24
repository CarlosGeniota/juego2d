/**
 * @deprecated
 * 
 * Esta clase está en desuso. Se usaba en versiones previas al motor de 
 * movimiento y de colisiones. Gestionaba el movimiento del personaje por el 
 * nivel en lugar de mover el fondo manteniendo al personaje centrado. Se 
 * descartó porque en ocasiones no funcionaba bien. Pese a todo, podría añadirse
 * en futuras versiones como ampliación del motor de movimiento.
 */
package proceso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author entornos
 */
public class Tablero extends JPanel implements Runnable{
    
    private PersonajeJugable pj;
    private Nivel nivel;
    private Sprite[] moneda;
    
    private Thread hilo;
    
    private final int DELAY=500;
        
    public Tablero(){
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        moneda = new Sprite[4];
        for(int i=0; i<4; i++){
            int randX = (int) (Math.random()*880)+1;
            int randY = (int) (Math.random()*590)+1;
            moneda[i] = new Sprite("moneda.jpg", randX, randY);
        }
        
        nivel = new Nivel("bg.jpg", moneda);
        
        pj = new PersonajeJugable("sprite.png", 250, 250);
        //moneda = new Sprite("moneda.jpg", 100, 40);
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        hilo = new Thread(this);
        hilo.start();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        nivel.addNivel(g2);
        
        // Ponemos por ultimo al pj para que esté por encima de todos
        pj.putSprite(g2);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    
    public void keyPressed(KeyEvent e) {
        
        // Shift (correr)
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            pj.setVelocidad(2);
        }
        
        // Derecha (pressed)
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && pj.getX() >= 400) {
            nivel.setBgDerecha(true);
            if(nivel.getX() > -(nivel.getBackground().getWidth(this) - 500)) {
                pj.keyReleased(e);
            } else {
                pj.keyPressed(e);
            }
        } else
        //Izquierda (pressed)
        if(e.getKeyCode() == KeyEvent.VK_LEFT && pj.getX() <= 100) {
            nivel.setBgIzquierda(true);
            if(nivel.getX() < 0) {
                pj.keyReleased(e);
            } else {
                pj.keyPressed(e);
            }
        } else
        // Arriba (pressed)
        if(e.getKeyCode() == KeyEvent.VK_UP && pj.getY() <= 100 ) {
            nivel.setBgArriba(true);
            if(nivel.getY() < 0) {
                pj.keyReleased(e);
            } else {
                pj.keyPressed(e);
            }
        } else
        // Abajo (pressed)
        if(e.getKeyCode() == KeyEvent.VK_DOWN && pj.getY() >= 400) {
            nivel.setBgAbajo(true);
            if(nivel.getX() > -(nivel.getBackground().getHeight(this) - 500)) {
                pj.keyReleased(e);
            } else {
                pj.keyPressed(e);
            }
            
        } else {
            pj.keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {
        
        // Shift (correr)
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            pj.setVelocidad(1);
        }
        
        // Derecha (Released)
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && pj.getX() >= 400) {
            nivel.setBgDerecha(false);
            pj.keyReleased(e);
        } else
        // Izquierda (Released)
        if(e.getKeyCode() == KeyEvent.VK_LEFT && pj.getX() <= 100) {
            nivel.setBgIzquierda(false);
            pj.keyReleased(e);
        } else
        // Arriba (Released)
        if(e.getKeyCode() == KeyEvent.VK_UP && pj.getY() <= 100 ) {
            nivel.setBgArriba(false);
            pj.keyReleased(e);
        } else
        // Abajo (Released)
        if(e.getKeyCode() == KeyEvent.VK_DOWN && pj.getY() >= 400) {
            nivel.setBgAbajo(false);
            pj.keyReleased(e);
            
        } else {
            pj.keyReleased(e);
        }
}
    
    public void mueveFondo() {
        
        if(nivel.isBgDerecha() && nivel.getX() > -(nivel.getBackground().getWidth(this) - 500) ) {
            nivel.setX(nivel.getX() - pj.getVelocidad());
        }
        
        if(nivel.isBgIzquierda() && nivel.getX() < 0) {
            nivel.setX(nivel.getX() + pj.getVelocidad());
        }
        
        if(nivel.isBgArriba() && nivel.getY() < 0) {
            nivel.setY(nivel.getY() + pj.getVelocidad());
        }
        
        if(nivel.isBgAbajo() && nivel.getY() > -(nivel.getBackground().getHeight(this) - 500 - pj.getHeight()) ) {
            nivel.setY(nivel.getY() - pj.getVelocidad());
        }
    }
    
    @Override
    public void run() {
        // AQUI EL CICLO DE EJECUCION DEL JUEGO
        long tiempo_act,tiempo_dif,sleep;
        tiempo_act = System.currentTimeMillis();
        
        while(true){
            
            pj.actualiza();
            nivel.actualiza();
            mueveFondo();
            repaint();
            
            tiempo_dif = System.currentTimeMillis() - tiempo_act;
            sleep = DELAY - tiempo_dif;
            if(DELAY > 0){
                sleep = 10;
            }
            
            try{
                Thread.sleep(sleep);
            }catch(InterruptedException err){
                System.out.println(err);
            }
            
            tiempo_act = System.currentTimeMillis();        
        }
    }
}
