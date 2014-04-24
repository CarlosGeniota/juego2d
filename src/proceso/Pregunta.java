/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

import java.util.ArrayList;

/**
 *
 * @author entornos
 */
public class Pregunta {
    private String texto;
    private int valor;
    private ArrayList<Respuesta> respuesta;
    private int seleccionada;
    
    private boolean combate;
    private boolean despide;

    
    public Pregunta() {
        this.texto = new String();
        this.valor = 0;
        this.respuesta = new ArrayList<>();
        this.seleccionada = 0;
        this.combate = false;
        this.despide = false;
    }

    public Pregunta(String pregunta) {
        this.texto = pregunta;
        this.valor = 0;
        this.respuesta = new ArrayList<>();
        this.seleccionada = 0;
        this.combate = false;
        this.despide = false;
    }

    public Pregunta(String pregunta, ArrayList<Respuesta> respuesta) {
        this.texto = pregunta;
        this.valor = 0;
        this.respuesta = respuesta;
        this.seleccionada = 0;
        this.combate = false;
        this.despide = false;
    }
    

    public String getTexto() {
        return texto;
    }

    public void setTexto(String pregunta) {
        this.texto = pregunta;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public ArrayList<Respuesta> getRespuesta() {
        return respuesta;
    }
    
    public Respuesta getRespuesta(int i) {
        return respuesta.get(i);
    }

    public void setRespuesta(ArrayList<Respuesta> respuesta) {
        this.respuesta = respuesta;
    }
    
    public void addRespuesta(Respuesta respuesta) {
        this.respuesta.add(respuesta);
    }
    
    public boolean delRespuesta(Respuesta respuesta) {
        return this.respuesta.remove(respuesta);
    }

    public int getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(int seleccionada) {
        this.seleccionada = seleccionada;
    }

    public boolean isCombate() {
        return combate;
    }

    public void setCombate(boolean combate) {
        this.combate = combate;
    }

    public boolean isDespide() {
        return despide;
    }

    public void setDespide(boolean despide) {
        this.despide = despide;
    }
}
