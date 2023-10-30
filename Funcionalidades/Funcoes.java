
import java.lang.reflect.InvocationTargetException;

import Inimigos.Elemental;
import Inimigos.Inimigos;
import Personagens.Knight;
import Personagens.Personagem;

public class Funcoes {
    public static int Rodadas;


    // Função que faz as lutas entre os Personagens e Inimigos
    public void batalha(Inimigos i, Personagem p) throws NoSuchMethodException, IllegalAccessException{
        while(p.getVida() > 0 && i.getVida() > 0){
            try {
                Rodadas++;
                p.getAtaque(p, i);
                i.getAtaque(i, p);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        Rodadas = 0;
    }
    
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException {
        Knight g = new Knight();
        Personagem p = g;
        Elemental e = new Elemental(g);
        Funcoes f = new Funcoes();
         try{
            f.batalha(e, p);
        }catch(IllegalArgumentException | IllegalAccessException i){
            System.out.println(i);

        }
    }
}
