/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class Marco extends JPanel implements Runnable {
   
    private boolean debug = true;
    
    private PersonajeJugable pj;
    private Nivel nivel;
    private Conversacion conversacion;
    
    // 1. Movimiento; 2. Conversacion; 3. Combate
    private int vista;
    
    private boolean parar = false; // Indica si el juego se está ejecutando o se para
    private boolean pausa = false;
    private boolean inventario = false;
    private int contenedor = -1;
    
    private Thread hilo;
    
    private final int DELAY=500;
    
    public Marco(){
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        // CARGAR NIVEL
        nivel = CargaNivel.cargaNivel();
        vista = CargaNivel.cargaVista();
        
        // CARGAR CONVERSACION
        Sprite pnj = nivel.getSprite(27);
        conversacion = new Conversacion("images/conver/playa.jpg", pnj);
        
        Dialogo dialogo = new Dialogo();
        
        Pregunta preg1 = new Pregunta();
        preg1.setTexto("Hola, grumete. No me suena tu cara asi que debes \n de ser nuevo por aqui.");
        
        Pregunta preg2 = new Pregunta();
        preg2.setTexto("En esta isla solo hay piratas, asi que impera la ley \n del más fuerte.");
            Respuesta resp21 = new Respuesta();
            resp21.setTexto("¡Eh! ¡Yo soy un auténtico pirata!");
            resp21.setValor(1);
            
            Respuesta resp22 = new Respuesta();
            resp22.setTexto("Gracias por el consejo. Lo tendré en cuenta");
            resp22.setValor(0);
            
            preg2.addRespuesta(resp21);
            preg2.addRespuesta(resp22);
        
        Pregunta preg3 = new Pregunta();
        preg3.setTexto("Ten cuidado por donde pisas, y aun mas con quien \n hablas.");
        preg3.setValor(0);
        
        Pregunta preg4 = new Pregunta();
        preg4.setTexto("¡ARR! ¡NO TOLERARÉ MÁS INSOLECIAS DE UN ENANO \n COMO TU!");
        preg4.setValor(1);
            Respuesta resp41 = new Respuesta();
            resp41.setTexto("¿A quién llamas ENANO, pelo estropajo?");
            // resp41.setValor(2);
            resp41.setValor(1);
            
            /*
            Respuesta resp42 = new Respuesta();
            resp42.setTexto("Psé... *Mirarle con desprecio e irse*);
            resp42.setValor(1);
            */
            
            Respuesta resp43 = new Respuesta();
            resp43.setTexto("Meh... *agachar la cabeza e irse*");
            resp43.setValor(0);
            
            Respuesta resp44 = new Respuesta();
            resp44.setTexto("No pretendía ofenderle, buen señor...");
            resp44.setValor(-1);
            
            preg4.addRespuesta(resp41);
            // preg4.addRespuesta(resp42);
            preg4.addRespuesta(resp43);
            preg4.addRespuesta(resp44);
            
        Pregunta preg5 = new Pregunta();
        preg5.setTexto("¿¡QUE!? Espero que manejes la espada mejor que \n la lengua...");
        preg5.setValor(2);
        preg5.setCombate(true);
        
        Pregunta preg6 = new Pregunta();
        preg6.setTexto("¡LÁRGATE! -Psé... Enanos que se creen piratas...-");
        preg6.setValor(1);
        preg6.setDespide(true);
        
        ArrayList<Pregunta> preg = new ArrayList<>();
            preg.add(preg1);
            preg.add(preg2);
            preg.add(preg3);
            preg.add(preg4);
            preg.add(preg5);
            preg.add(preg6);
                    
        dialogo.setPreguntas(preg);
        
        conversacion.setDialogo(dialogo);
        
        
        pj = new PersonajeJugable("sprites/abajo1.png", 250-16, 250-16);
    }

    public int getVista() {
        return vista;
    }

    public void setVista(int vista) {
        this.vista = vista;
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
        
        // PINTAR NIVEL
        switch(vista) {
            case 1: 
                // Movimiento
                
                // Pintar nivel y personaje
                nivel.addNivel(g2);
                pj.putSprite(g2);
                
                // Inventario
                Interfaz.pintaInventario(nivel, pj, g2, inventario, contenedor);
                
                break;
                
            case 2:
                try {
                    // Conversacion
                    this.vista = conversacion.pintaConversacion(g2, pj);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Marco.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case 3:
                // Combate
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, 500, 500);
                g2.setColor(Color.WHITE);
                
                Font fuente = g2.getFont();
                
                g2.setFont(new Font("Courier", Font.BOLD, 18));
                g2.drawString("COMBATE", 110, 120);
                g2.setFont(new Font("Courier", Font.BOLD, 14));
                g2.drawString("ESTA ACCION NO ESTÁ AUN PROGRAMADA", 80, 150);
                
                g2.setFont(fuente);
                g2.drawString("<pulsa x para volver>", 140, 180);
                
                break;
        }
        
        // MENU JUEGO
        Interfaz.pintaMenuJuego(pj, g2);
        
        // MENU DEBUG
        Interfaz.pintaDebug(nivel, pj, g2, debug);
        
        // Pausa
        Interfaz.pintaPausa(g2, pausa);
        
        // Game Over
        Interfaz.pintaGameOver(pj, g);
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if(this.pausa) 
                this.pausa = false;
            else
                this.pausa = true;
        }
        
        if(pausa) {
            //
            int respuesta = Interfaz.keyPressed(e);
            
            if(respuesta == 1) {
                System.exit(0);
            } else if (respuesta == 2) {
                this.pausa = false;
            }
            
        } else {
            switch(vista) {
                case 1: 
                    // Movimiento por el mapa

                    if(e.getKeyCode() == KeyEvent.VK_E){
                        if(this.inventario) {
                            this.inventario = false;
                            if(this.contenedor != -1)
                                this.contenedor = -1;
                        } else
                            this.inventario = true;
                    } 

                    if(e.getKeyCode() == KeyEvent.VK_D) {
                        if(contenedor == -1) {
                            contenedor = Colisiones.compruebaContenedor(nivel, pj);
                            if(contenedor != -1)
                                inventario = true;
                        } else {
                            contenedor = -1;
                            inventario = false;
                        }
                    }

                    if(inventario) {
                        if(contenedor != -1)
                            Inventario.keyPressed(nivel, nivel.getSprite(contenedor), e);
                        else
                            Inventario.keyPressed(nivel, pj, e);
                    } else {
                        pj.keyPressed(nivel, e);
                    }

                    if(e.getKeyCode() == KeyEvent.VK_A) {
                        // HABLAR CON PERSONAJE :)
                        // Simialr a contenedor, peeeero, en vez de poner el inventario a on, ponemos la  variable vista a 2
                        if(Colisiones.compruebaConversacion(nivel, pj) != -1) {
                            pj.anima("arriba");
                            this.vista = 2;
                        }
                    }

                    Movimiento.keyPressed(nivel, pj, e);

                    break;

                case 2:
                    // Conversacion
                    conversacion.keyPressed(e);

                    if(e.getKeyCode() == KeyEvent.VK_X){
                        this.vista = 1;
                    }

                    break;

                case 3:
                    // Cobbate
                    // conversacion.keyPressed(e);

                    if(e.getKeyCode() == KeyEvent.VK_X){
                        this.vista = 1;
                    }

                    break;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if(!pausa) {
            switch(vista) {
                case 1:
                    Movimiento.keyReleased(nivel, pj, e); 
                    break;
                    
                case 2:
                    break;
                    
                case 3:
                    break;
            }
        }
}
    
    
    
    @Override
    public void run() {
        // AQUI EL CICLO DE EJECUCION DEL JUEGO
        long tiempo_act,tiempo_dif,sleep;
        tiempo_act = System.currentTimeMillis();
        
        while(true){
//            while(!pausa) {
                
                if(pausa || contenedor != -1 || pj.getSalud() == 0) 
                    parar = true;
                else
                    parar = false;
                
                
                pj.actualiza();
                nivel.actualiza();
                Colisiones.comprobaciones(nivel, pj);
                if(!parar)
                    Movimiento.mueveFondo(nivel, pj);
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
//            }
        }
    }
}

