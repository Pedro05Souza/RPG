package Personagens;
import Inimigos.Elemental;

public class testeMain{
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
       Mago m = new Mago();
       Elemental e = new Elemental(m);
       m.atacar(e);



    }
}