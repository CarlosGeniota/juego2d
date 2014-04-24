/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author entornos
 */
public class Ventana extends JFrame {
    
    public Ventana(){
        setTitle("Mi juego 2D"); // Titulo de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Salir del programa al cerrar la ventana
        setSize(500,500); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centramos la ventana en pantalla
        setResizable(false); // Evitamos que se redimensione la ventana
        
        // Agregamos un tablero al juego
        // final Tablero tablero = new Tablero();
        final Marco tablero = new Marco();
        add(tablero);
        
        // tablero.run();
        
        // Hacemos visible la ventana
        setVisible(true);
        
        this.addKeyListener(new java.awt.event.KeyListener() {

                @Override
                public void keyPressed(KeyEvent e) {
                    tablero.keyPressed(e);                    
                }

                @Override
                public void keyTyped(KeyEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
 
               @Override
                public void keyReleased(KeyEvent e) {
                    tablero.keyReleased(e);
                }

            });
            
        
    }
    
    public static void main(String args[]){
        new Ventana();
    }
}
