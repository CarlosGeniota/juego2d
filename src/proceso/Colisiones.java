/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proceso;


/**
 *
 * @author Carlos
 */
public class Colisiones {
    
    private static int pain;
    private static int idPain = -1;
    
    public static void comprobaciones(Nivel nivel, PersonajeJugable pj) {
        compruebaComidos(nivel,pj);
        compruebaCogidos(nivel,pj);
        compruebaSalud(nivel,pj);
    }
    
    
    public static void compruebaComidos(Nivel nivel, PersonajeJugable pj) {
        for(int i=0; i < nivel.getInventario().numObjetos(); i++){
            if(nivel.getSprite(i).getX()-10 < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
               pj.getX() < (nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null) + 10)) {
                
                if(nivel.getSprite(i).getY()-10 < ( pj.getY() + pj.getImagen().getHeight(null) ) && 
                   pj.getY() < (nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null) + 10)) {
                    
                    if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isComible()) {
                        
                        // Vida
                        if(nivel.getSprite(i).getSalud() > 0) {
                            pj.addSalud(nivel.getSprite(i).getSalud());
                        }
                        
                        // Puntos
                        if(nivel.getSprite(i).getPuntos() > 0) {
                            pj.sumaPuntos(nivel.getSprite(i).getPuntos());
                        }

                        nivel.getSprite(i).setVisible(false);

                    }
                    
                }
            }
        }
    }
    
    public static void compruebaCogidos(Nivel nivel, PersonajeJugable pj) {
        for(int i=0; i < nivel.getInventario().numObjetos(); i++){
            if(nivel.getSprite(i).getX()-10 < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
               pj.getX() < (nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null) + 10)) {
                
                if(nivel.getSprite(i).getY()-10 < ( pj.getY() + pj.getImagen().getHeight(null) ) && 
                   pj.getY() < (nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null) + 10)) {
                    
                    if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isCogible() && pj.getInventario().numObjetos() < pj.getInventario().getMax()) {
                        // pj.sumaPuntos(nivel.getSprite(i).getPuntos());
                        pj.getInventario().addObjeto(nivel.getSprite(i));
                        nivel.getInventario().quitaObjeto(i);
                    }
                    
                }
            }
        }
    } 
    
    public static int compruebaConversacion(Nivel nivel, PersonajeJugable pj) {
        for(int i=0; i < nivel.getInventario().numObjetos(); i++){
            if(nivel.getSprite(i).getX()-10 < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
               pj.getX() < (nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null) + 10)) {
                
                if(nivel.getSprite(i).getY()-10 < ( pj.getY() + pj.getImagen().getHeight(null) ) && 
                   pj.getY() < (nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null) + 10)) {
                    
                    if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isConversable() ) {
                        return i;
                    }
                    
                }
            }
        }
        
        return -1;
    } 
    
    
    public static void compruebaSalud(Nivel nivel, PersonajeJugable pj) {
        for(int i=0; i < nivel.getInventario().numObjetos(); i++){
            if(nivel.getSprite(i).getX() < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
               pj.getX() < (nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null))) {
                
                if(nivel.getSprite(i).getY() < ( pj.getY() + pj.getImagen().getHeight(null) + 8) && 
                   pj.getY() < (nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null) - (pj.getImagen().getHeight(null) - 15) )) {
                    
                    if(nivel.getSprite(i).isVisible() && !nivel.getSprite(i).isComible()) {
                        
                        // Vida
                        if(pain == 0) {
                            pj.addSalud(nivel.getSprite(i).getSalud());
                            pain = nivel.getSprite(i).getSalud();
                            idPain = i;
                            nivel.getSprite(i).setSalud(0);
                        } 
                        
                        // Puntos
                        if(nivel.getSprite(i).getPuntos() < 0)
                            pj.sumaPuntos(nivel.getSprite(i).getPuntos());
                        
                        if(nivel.getSprite(i).isComible()) {
                            nivel.getSprite(i).setVisible(false);
                        }

                    }
                }
            }
        }
        
        if(pain != 0 && idPain > -1) {
            if(nivel.getSprite(idPain).getX() < ( pj.getX() + pj.getImagen().getWidth(null) + 10 ) && 
               pj.getX() < (nivel.getSprite(idPain).getX() + nivel.getSprite(idPain).getImagen().getWidth(null) +10 )) {
                
                if(nivel.getSprite(idPain).getY() < ( pj.getY() + pj.getImagen().getHeight(null) + 10 ) && 
                   pj.getY() < (nivel.getSprite(idPain).getY() + nivel.getSprite(idPain).getImagen().getHeight(null))) {
                    
                    // Está sobre los pinchos
                    
                } else {
                    
                    // No está sobre los pinchos
                    nivel.getSprite(idPain).setSalud(pain);
                    pain = 0;
                    idPain = -1;
                    
                }
            } else {
                // No está sobre los pinchos
                nivel.getSprite(idPain).setSalud(pain);
                pain = 0;
                idPain = -1;

            }
        }
        
    }
    
    
    public static int compruebaContenedor(Nivel nivel, PersonajeJugable pj) {
        for(int i=0; i < nivel.getInventario().numObjetos(); i++){
            if(nivel.getSprite(i).getX()-10 < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
               pj.getX() < (nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null) + 10)) {
                
                if(nivel.getSprite(i).getY()-10 < ( pj.getY() + pj.getImagen().getHeight(null) ) && 
                   pj.getY() < (nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null) + 10)) {
                    
                    if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isContenedor()) {
                        // System.out.println("Esto es un objeto que tiene cosas por dentro... ("+i+")");
                        return i;
                    }
                    
                }
            }
        }
        return -1;
    }
    
    public static boolean compruebaColision(Nivel nivel, PersonajeJugable pj, int posicion) {
        
        for(int i=0; i < nivel.getInventario().numObjetos(); i++){
            
            switch(posicion) {
                case 1:     // Comprobamos colisiones por la izquierda
                    if((nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null) + 5) > pj.getX() &&
                        nivel.getSprite(i).getX() < pj.getX()) {
                        
                         if(nivel.getSprite(i).getY() < ( pj.getY() + pj.getImagen().getHeight(null)) && 
                            pj.getY() + pj.getHeight() < (nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null) )) {

                             if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isSolido()) {
                                 //System.out.println("Bloqueado por la iquierda:");
                                 // System.out.println("PJ X: "+pj.getX()+", Y: "+pj.getY());
                                 // System.out.println("Fin sprite X: "+(nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(this))+", Yarr: "+ nivel.getSprite(i).getY() +", Yaba: "+(nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(this)));
                                 //pj.setSprite("sprites/izquierda1.png");
                                 pj.anima("izquierda");
                                 return true;
                             }

                         }
                    }
                    break;
                    
                case 2:     // Comprobamos colisiones por arriba
                    if(nivel.getSprite(i).getX() < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
                        pj.getX() < (nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null))) {

                         if((nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null)) - pj.getHeight() > pj.getY() - 5 &&
                            nivel.getSprite(i).getY() < pj.getY()) {

                             if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isSolido()) {
                                 pj.anima("arriba");
                                 return true;
                             }

                         }
                    }
                    break;
                    
                case 3:     // Comprobamos colisiones por la derecha
                    if(nivel.getSprite(i).getX() - 5 < pj.getX() + pj.getImagen().getWidth(null) &&
                       pj.getX() + pj.getImagen().getWidth(null) < nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null)) {
                        
                         if(nivel.getSprite(i).getY() < ( pj.getY() + pj.getImagen().getHeight(null) /* - pj.getHeight() */ ) && 
                            pj.getY() < (nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null) - pj.getHeight() )) {

                             if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isSolido()) {
                                 // System.out.println("Bloqueado por la derecha");
                                 //pj.setSprite("sprites/derecha1.png");
                                 pj.anima("derecha");
                                 return true;
                             }

                         }
                    }
                    break;
                    
                case 4:     // Comprobamos colisiones por abajo
                    if(nivel.getSprite(i).getX() < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
                        pj.getX() < (nivel.getSprite(i).getX() + nivel.getSprite(i).getImagen().getWidth(null))) {

                         if(nivel.getSprite(i).getY() - 5 < pj.getY() + pj.getImagen().getHeight(null) &&
                            pj.getY() + pj.getImagen().getHeight(null) < nivel.getSprite(i).getY() + nivel.getSprite(i).getImagen().getHeight(null)) {

                             if(nivel.getSprite(i).isVisible() && nivel.getSprite(i).isSolido()) {
                                 // System.out.println("Bloqueado por abajo");
                                 //pj.setSprite("sprites/abajo1.png");
                                 pj.anima("abajo");
                                 return true;
                             }

                         }
                    }
                    break;
                    
                default:    // Comprobamos todo tipo de colisiones
                    compruebaColision(nivel, pj, nivel.getSprite(i));
                    break;
            }
        }
        return false;
    }
    
    
    public static boolean compruebaColision(Nivel nivel, PersonajeJugable pj, Sprite objeto) {
        // Comprobamos todo tipo de colisiones
        if(objeto.getX() < ( pj.getX() + pj.getImagen().getWidth(null) ) && 
            pj.getX() < (objeto.getX() + objeto.getImagen().getWidth(null))) {

             if(objeto.getY() < ( pj.getY() + pj.getImagen().getHeight(null) ) && 
                pj.getY() < (objeto.getY() + objeto.getImagen().getHeight(null))) {

                 if(objeto.isVisible() && objeto.isSolido()) {
                     System.out.println("Colision :)");
                     return true;
                 }

             }
         }
        
        return false;
    }
    
    
}
