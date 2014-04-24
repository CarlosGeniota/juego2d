/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

/**
 *
 * @author entornos
 */
public class Respuesta {
    private String texto;
    private int valor;
    // De primeras usamos solo "valor", pero luego usaremos todos estos atributos
    // private int ingenio;
    // private int maldad;

    public Respuesta() {
        this.texto = new String();
        this.valor = 0;
    }
    
    public Respuesta(String texto) {
        this.texto = texto;
        this.valor = 0;
    }
    
    public Respuesta(String texto, int valor) {
        this.texto = texto;
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
