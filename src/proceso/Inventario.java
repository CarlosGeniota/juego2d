/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Carlos
 */
public class Inventario {
    
    ArrayList<Sprite> inventario;
    int selected;
    int max;
    
    public Inventario() {
        this.inventario = new ArrayList<>();
        this.selected = -1;
        this.max = -1;
    }
    
    public Inventario(Sprite[] array) {
        inventario = new ArrayList<>();
        inventario.addAll(Arrays.asList(array));
        this.selected = -1;
        this.max = -1;
    }

    public ArrayList<Sprite> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Sprite> objeto) {
        this.inventario = objeto;
    }
    
    public void setInventario(Sprite[] array) {
        this.inventario.addAll(Arrays.asList(array));
    }
    
    public Sprite getObjeto(int i) {
        return inventario.get(i);
    }
    
    public void quitaObjeto(int i) {
        this.inventario.remove(i);
    }
    
    public void addObjeto(Sprite objeto) {
        if(inventario.size() < max || max == -1)
            inventario.add(objeto);
    }
    
    public int numObjetos() {
        return this.inventario.size();
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
    
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public static void menu(Graphics2D g2, Sprite pj){
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 400, 500, 100);
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 400, 500, 100);
            
        for(int i = 0; i<pj.getInventario().numObjetos(); i++) {
            int j = 425;
            
            if(i == pj.getInventario().getSelected())
                g2.drawRect(5+i*50, j, pj.getInventario().getObjeto(i).getWidth(), pj.getInventario().getObjeto(i).getWidth());
            
            g2.drawString(String.valueOf(i+1%10), 5+i*50, j-5);
            pj.getInventario().getObjeto(i).putSprite(g2, 5+i*50, j);
            // System.out.println("Impreso en: "+ (20+(i*20)) +", "+j);
        }
        
        if(pj.getInventario().getSelected() >= pj.getInventario().numObjetos())
            pj.getInventario().setSelected(pj.getInventario().numObjetos()-1);
        else if(pj.getInventario().getSelected()<0 && pj.getInventario().numObjetos() > 0)
            pj.getInventario().setSelected(0);
    }
    
    public static void keyPressed(Nivel nivel, PersonajeJugable pj, KeyEvent e) {
       // Equipar un objeto
       if(e.getKeyCode() == KeyEvent.VK_W) {
           // System.out.println("hum... debería equipar esta mierda, Jimmy... ("+pj.getInventario().getSelected()+")");
           if(pj.getInventario().getSelected() < pj.getInventario().numObjetos() && pj.getInventario().getSelected() >= 0) {
               pj.setEquipado(pj.getInventario().getSelected());
           }
       }
       
       keyPressed(nivel, (Sprite) pj, e);
    }
    
    public static void keyPressed(Nivel nivel, Sprite pj, KeyEvent e) {
        
        // Seleccionar items
        if(e.getKeyCode() == KeyEvent.VK_1) {
            pj.getInventario().setSelected(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_2) {
            pj.getInventario().setSelected(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_3) {
            pj.getInventario().setSelected(2);
        }
        if(e.getKeyCode() == KeyEvent.VK_4) {
            pj.getInventario().setSelected(3);
        }
        if(e.getKeyCode() == KeyEvent.VK_5) {
            pj.getInventario().setSelected(4);
        }
        if(e.getKeyCode() == KeyEvent.VK_6) {
            pj.getInventario().setSelected(5);
        }
        if(e.getKeyCode() == KeyEvent.VK_7) {
            pj.getInventario().setSelected(6);
        }
        if(e.getKeyCode() == KeyEvent.VK_8) {
            pj.getInventario().setSelected(7);
        }
        if(e.getKeyCode() == KeyEvent.VK_9) {
            pj.getInventario().setSelected(8);
        }
        if(e.getKeyCode() == KeyEvent.VK_0) {
            pj.getInventario().setSelected(9);
        }
        
        
        // Tirar un objeto (seleccionado)
        if(e.getKeyCode() == KeyEvent.VK_Q) {
 
            if(pj.getInventario().getSelected() < pj.getInventario().numObjetos() && pj.getInventario().getSelected() >= 0) {
            // if(pj.getInventario().getSelected() != -1 && pj.getInventario().numObjetos() > pj.getInventario().getSelected())
                try {
                    pj.tirarObjeto(nivel, pj.getInventario().getSelected());
                } catch (Exception ex) {
                    // System.out.println("Excepcion: " + ex);
                }
            }
        }
        
    }
}
