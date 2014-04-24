/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author entornos
 */
public class Interfaz {
    
    static int opcPausa = 0;
    static int maxPausa = 2;
    
    public static void pintaMenuJuego(PersonajeJugable pj, Graphics g) {
        /* Puntos */
        pintaPuntos(pj, g);
        
        /* Salud */
        pintaSalud(pj, g);
        
        /* Equipado */
        pintaEquipado(pj, g);
    }
    
    public static void pintaPuntos(PersonajeJugable pj, Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(3, 3, 75, 24);
        g.setColor(Color.BLACK);
        g.drawRect(3, 3, 75, 24);
        Sprite puntos = new Sprite("moneda.jpg", 0, 0);
        puntos.putSprite(g, 9, 7);
        g.drawString(""+pj.getPuntos(), 34, 19);
    }
    
    public static void pintaSalud(PersonajeJugable pj, Graphics g) {
        
        int numCorazones = pj.getSalud() / 2;
        int medioCorazon = pj.getSalud() % 2;
        
        for(int i=0; i<numCorazones; i++) {
            Sprite corazon = new Sprite();
            corazon.setSprite("sprites/corazon-entero.png");
            corazon.putSprite(g, 100+(20*i), 8);
        }
        
        if(medioCorazon == 1) {
            Sprite mCorazon = new Sprite();
            mCorazon.setSprite("sprites/corazon-medio.png");
            mCorazon.putSprite(g, 100+(20*numCorazones), 8);
        }
    }
    
    public static void pintaEquipado(PersonajeJugable pj, Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(440, 3, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(440, 3, 50, 50);
        if(pj.getEquipado() != null) {
            pj.getEquipado().putSprite(g, 445, 8);
        }
    }

    public static void pintaInventario(Nivel nivel, PersonajeJugable pj, Graphics2D g, boolean inventario, int contenedor) {
        if(inventario) {
            // Contenedor
            if(contenedor != -1) 
                Inventario.menu(g, nivel.getInventario().getObjeto(contenedor));
            else 
                Inventario.menu(g, pj);
        }
    }
    
    public static void pintaDebug(Nivel nivel, PersonajeJugable pj, Graphics g, boolean debug) {
        if(debug) {
            g.setColor(Color.WHITE);
            g.fillRect(400, 430, 100, 400);
            g.setColor(Color.BLACK);
            g.drawString("X: "+(-nivel.getX()+pj.getX()), 405, 450);
            g.drawString("Y: "+(-nivel.getY()+pj.getY()), 405, 460);
        }
        
    }
    
    public static void pintaPausa(Graphics g, boolean pausa) {
        if(pausa) {
            g.setColor(Color.GRAY);
            g.fillRect(180, 225, 140, 90);
            g.setColor(Color.WHITE);
            g.drawRect(180, 225, 140, 90);
            g.drawString("Juego Pausado", 208, 255);
            if(opcPausa == 0) {
                g.setFont(g.getFont().deriveFont(Font.ITALIC));
                g.drawString("-> Salir", 208, 275);
                g.setFont(g.getFont().deriveFont(Font.PLAIN));
                g.drawString("Volver", 208, 290);
            } else {
                g.drawString("Salir", 208, 275);
                g.setFont(g.getFont().deriveFont(Font.ITALIC));
                g.drawString("-> Volver", 208, 290);
                g.setFont(g.getFont().deriveFont(Font.PLAIN));
            }
        }
    }
    
    public static void pintaGameOver(PersonajeJugable pj, Graphics g) {
        if(pj.getSalud() == 0) {
            g.setColor(Color.GRAY);
            g.fillRect(180, 225, 140, 50);
            g.setColor(Color.WHITE);
            g.drawRect(180, 225, 140, 50);
            g.drawString("Game Over", 218, 255);
        }
    }
    
    public static int keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            opcPausa = (opcPausa+(maxPausa-1))%maxPausa; // seleccionada--
        }
        
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            opcPausa = (opcPausa+1)%maxPausa; // seleccionada--
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            return opcPausa+1;
        }
        return 0;
    }
}
