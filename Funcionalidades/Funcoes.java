
import java.lang.reflect.InvocationTargetException;

import Inimigos.Elemental;
import Inimigos.Inimigos;
import Personagens.Mago;
import Personagens.Personagem;

public class Funcoes {


    // Função que faz as lutas entre os Personagens e Inimigos
    public void batalha(Inimigos i, Personagem p) throws NoSuchMethodException, IllegalAccessException{
        while(p.getVida() > 0 && i.getVida() > 0){
            try {
                p.getAtaque(p, i);
                i.getAtaque(i, p);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException {
        Mago m = new Mago();
        Elemental e = new Elemental(m);
        Funcoes f = new Funcoes();
         try{
            f.batalha(e, m);
        }catch(IllegalArgumentException | IllegalAccessException i){
            System.out.println(i);

        }
    }
}
