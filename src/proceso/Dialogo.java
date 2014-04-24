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
public class Dialogo {
    
    private ArrayList<Pregunta> preguntas;
    private int valor;
    private int estado;
    
    public Dialogo() {
        this.preguntas = new ArrayList<>();
        this.valor = 0;
        this.estado = 0;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    public Pregunta getPregunta(int i) {
        return preguntas.get(i);
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public void addValor(int sumar) {
        this.valor += sumar;
    }
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
