package Personagens;
import Inimigos.Elemental;

public class testeMain{
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
       Mago m = new Mago();
       Elemental a = new Elemental(m);
       a.setAtributosInimigo();
       System.out.println(a.getVida());
       System.out.println(a.getArmadura());
       System.out.println(a.getDano());
       System.out.println(a.getNivel());
       System.out.println(a.getPontos());


    }
}