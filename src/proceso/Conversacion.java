/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author entornos
 */
public class Conversacion {
    private Inventario inventario;
    private Image background;
    private Sprite personaje;
    private Dialogo dialogo;
    
    private int vez;
    
    public Conversacion(String fondo, Sprite pnj) {
        ImageIcon background_ic = new ImageIcon(this.getClass().getResource(fondo));
        this.background = background_ic.getImage();
        
        this.inventario = new Inventario();
        
        this.personaje = pnj;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public Sprite getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Sprite personaje) {
        this.personaje = personaje;
    }

    public Dialogo getDialogo() {
        return dialogo;
    }

    public void setDialogo(Dialogo dialogo) {
        this.dialogo = dialogo;
    }
    
    
    /**
     * 
     * @param grafico
     * @param pj
     * @return int Entero que luego se llevará a la vista (1:mov, 2:conv; 3:comb;)
     */
    
    public int pintaConversacion(Graphics2D grafico, PersonajeJugable pj) throws InterruptedException {
        
        // Guardamos la fuente anterior
        Font fuente = grafico.getFont();
        
        // Pintamos el fondo
        grafico.drawImage(this.background, 0, 0, null);

        // Pintamos los personajes de la conversacion
        grafico.drawImage(this.personaje.getImagen(), 50, 300, null);
        grafico.drawImage(pj.getImagen(), 120, 400, null);
        
        // Pintamos la orden siguiente
        if(dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size() == 0) {
            if(this.haySiguiente())
                grafico.drawString("<pulsa ESPACIO>", 45, 60);
            else
                grafico.drawString("<pulsa X para salir>", 45, 60);
        } else {
            grafico.drawString("<usa las FLECHAS para seleccionar y ESPACIO para responder>", 45, 60);
        }
        
        // Pintamos el texto de la conversacion
        grafico.setFont(new Font("Courier", Font.BOLD, 16));
        this.drawString(grafico, dialogo.getPregunta(dialogo.getEstado()).getTexto(), 40, 70);
        
        // Respuestas:
        if(dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size() > 0) {
            
            grafico.setFont(new Font("Courier", Font.ITALIC, 14));
            
            for(int i=0; i<dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size(); i++) {
                if(dialogo.getPregunta(dialogo.getEstado()).getSeleccionada() == i) {
                    grafico.setFont(new Font("Courier", Font.BOLD, 14));
                    this.drawString(grafico, "-> " + dialogo.getPregunta(dialogo.getEstado()).getRespuesta(i).getTexto(), 160, 200+(i*grafico.getFontMetrics().getHeight()));
                    grafico.setFont(new Font("Courier", Font.ITALIC, 14));
                } else {
                    this.drawString(grafico, dialogo.getPregunta(dialogo.getEstado()).getRespuesta(i).getTexto(), 150, 200+(i*grafico.getFontMetrics().getHeight()));
                }
            }
        }
                
        // Acciones de conversación
        if(dialogo.getPregunta(dialogo.getEstado()).isCombate()) {
            // Combate
            if(vez == 0) {
               vez = 1;
            } else {
                Thread.sleep(1500);
                vez = 0;
                return 3;
            }
        }
        
        if(dialogo.getPregunta(dialogo.getEstado()).isDespide()) {
            // Despedida forzosa
            if(vez == 0) {
               vez = 1;
            } else {
                Thread.sleep(2000);
                vez = 0;
                return 1;
            }
        }
        
        // Restablecemos la fuente
        grafico.setFont(fuente);
        
        return 2;
        
    }
    
    private void avanzar() {
        if(dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size() == 0) {
            int sigEstado = dialogo.getEstado() + 1;
            while (sigEstado < dialogo.getPreguntas().size() && dialogo.getPregunta(sigEstado).getValor() != dialogo.getValor()) {
                sigEstado++;
            }
            if(sigEstado < dialogo.getPreguntas().size())
                dialogo.setEstado(sigEstado);
        }
    }
    
    private void responder() {
        // Modificamos el valor
        int seleccionada = dialogo.getPregunta(dialogo.getEstado()).getSeleccionada();
        dialogo.addValor(dialogo.getPregunta(dialogo.getEstado()).getRespuesta(seleccionada).getValor());
        
        // Y pasamos a la siguiente pregunta
        int sigEstado = dialogo.getEstado() + 1;
        while (sigEstado < dialogo.getPreguntas().size() && dialogo.getPregunta(sigEstado).getValor() != dialogo.getValor()) {
            sigEstado = (sigEstado+1)%dialogo.getPreguntas().size();
        }
        if(sigEstado < dialogo.getPreguntas().size())
            dialogo.setEstado(sigEstado);
        
        else {
            for(int i=0; i<dialogo.getPreguntas().size(); i++) {
                if(dialogo.getPregunta(i).getValor() == dialogo.getValor()) {
                    dialogo.setEstado(i);
                }
            }
        }
    }

    
    private boolean haySiguiente() {
        
        int sigEstado = dialogo.getEstado() + 1;
        
        while (sigEstado < dialogo.getPreguntas().size() && dialogo.getPregunta(sigEstado).getValor() != dialogo.getValor()) {
            sigEstado++;
        }
        
        if(sigEstado < dialogo.getPreguntas().size())
            return true;
        
        return false;
    }
    
    private void drawString(Graphics2D g, String text, int x, int y) {
                for (String line : text.split("\n"))
                    g.drawString(line, x, y += g.getFontMetrics().getHeight());
            }
    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(this.dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size() > 0) {
                this.responder();
            } else {
                this.avanzar();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(this.dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size() > 0) {
                int seleccionada = this.dialogo.getPregunta(dialogo.getEstado()).getSeleccionada();
                int max = this.dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size();
                seleccionada = (seleccionada+1) % max; // seleccionada++
                this.dialogo.getPregunta(dialogo.getEstado()).setSeleccionada(seleccionada);
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(this.dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size() > 0) {
                int seleccionada = this.dialogo.getPregunta(dialogo.getEstado()).getSeleccionada();
                int max = this.dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size();
                seleccionada = (seleccionada+(max-1))%max; // seleccionada--
                this.dialogo.getPregunta(dialogo.getEstado()).setSeleccionada(seleccionada);
            }
        }
        
        /*
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Pulsacion de Enter
        }
        * */
        
        if(e.getKeyCode() == KeyEvent.VK_X) {
            if(dialogo.getPregunta(dialogo.getEstado()).getRespuesta().size() != 0) {
                this.dialogo.setEstado(0);
            }
        }
    }
    
}
