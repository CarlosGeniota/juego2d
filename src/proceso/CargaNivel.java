/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;

/**
 *
 * @author entornos
 */
public class CargaNivel {
    public static Nivel cargaNivel() {
        
        // Cargar nivel
        Sprite [] moneda = new Sprite[28];
        
        for(int i=0; i<4; i++){
            int randX = (int) (Math.random()*1000)+300;
            int randY = (int) (Math.random()*1000)+350;
            moneda[i] = new Sprite("moneda.jpg", randX, randY);
            moneda[i].setComible(true);
            moneda[i].setPuntos(i+1);
        }
        moneda[4] = new Sprite("sprites/cofre.png", 400, 380);
        moneda[4].setSolido(true);
        moneda[4].setComible(false);
        moneda[4].setContenedor(true);
        for(int i=5; i<8; i++){
            int randX = (int) (Math.random()*1000)+300;
            int randY = (int) (Math.random()*1000)+350;
            moneda[i] = new Sprite("sprites/espada.png", randX, randY, true, "Espada");
            moneda[i].setCogible(true);
        }
        
        
        moneda[9] = new Sprite("sprites/map/palmera.png", 510, 180, true, "Arbol");
        moneda[9].setCogible(false);
        moneda[9].setSolido(true);
        
        moneda[8] = new Sprite("sprites/map/palmera1.png", 550, 200, true, "Arbol");
        moneda[8].setCogible(false);
        moneda[8].setSolido(true);
        
        moneda[10] = new Sprite("sprites/map/palmera.png", 540, 280, true, "Arbol");
        moneda[10].setCogible(false);
        moneda[10].setSolido(true);
        
        moneda[11] = new Sprite("sprites/map/palmera1.png", 525, 320, true, "Arbol");
        moneda[11].setCogible(false);
        moneda[11].setSolido(true);
        
        moneda[12] = new Sprite("sprites/map/palmera.png", 600, 400, true, "Arbol");
        moneda[12].setCogible(false);
        moneda[12].setSolido(true);
        
        
        moneda[13] = new Sprite("sprites/map/palmera.png", 350, 900, true, "Arbol");
        moneda[13].setCogible(false);
        moneda[13].setSolido(true);
        
        moneda[14] = new Sprite("sprites/map/palmera1.png", 520, 710, true, "Arbol");
        moneda[14].setCogible(false);
        moneda[14].setSolido(true);
        
        moneda[15] = new Sprite("sprites/map/palmera.png", 840, 625, true, "Piedra");
        moneda[15].setCogible(false);
        moneda[15].setSolido(true);
        
        for(int i=16; i<20; i++){
            int randX = (int) (Math.random()*1000)+300;
            int randY = (int) (Math.random()*1000)+350;
            moneda[i] = new Sprite("sprites/corazon-entero.png", randX, randY);
            moneda[i].setComible(true);
            moneda[i].setPuntos(0);
            moneda[i].setSalud(1);
        }
        
        moneda[20] = new Sprite("sprites/map/mar-0.png", 0, 0, true, "Mar");
        moneda[20].setCogible(false);
        moneda[20].setSolido(true);
        
        moneda[21] = new Sprite("sprites/map/mar-1.png", 0, 0, true, "Mar");
        moneda[21].setCogible(false);
        moneda[21].setSolido(true);
        
        moneda[22] = new Sprite("sprites/map/mar-2.png", 750, 0, true, "Mar");
        moneda[22].setCogible(false);
        moneda[22].setSolido(true);
        
        moneda[23] = new Sprite("sprites/map/mar-3.png", 1830, 0, true, "Mar");
        moneda[23].setCogible(false);
        moneda[23].setSolido(true);
        
        moneda[24] = new Sprite("sprites/map/mar-4.png", 3170, 0, true, "Mar");
        moneda[24].setCogible(false);
        moneda[24].setSolido(true);
        
        moneda[25] = new Sprite("sprites/map/mar-5.png", 3740, 0, true, "Mar");
        moneda[25].setCogible(false);
        moneda[25].setSolido(true);
        
        
        moneda[26] = new Sprite("sprites/pinchos.GIF", 1100, 530, true, "Pinchos");
        moneda[26].setCogible(false);
        moneda[26].setSolido(false);
        moneda[26].setPuntos(0);
        moneda[26].setSalud(-1);
        
        moneda[27] = new Sprite("sprites/pnj/pirata.png", 1400, 480, true, "Pirata");
        moneda[27].setCogible(false);
        moneda[27].setSolido(true);
        moneda[27].setConversable(true);
        
        
        Inventario invObjeto = new Inventario();
        Sprite banana = new Sprite("sprites/banana.gif", 399, 560);
        banana.setSolido(true);
        banana.setComible(false);
        banana.setCogible(true);
        invObjeto.addObjeto(banana);
        moneda[4].setIventario(invObjeto);
        
        // 70, 190
        
        Nivel nivel = new Nivel("playa.jpg", moneda);
        nivel.setX(-380);
        nivel.setY(-420);
        
        return nivel;
        
    }
    
    public static int cargaVista() {
        return 1;
    }
}
