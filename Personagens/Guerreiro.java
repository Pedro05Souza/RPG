package Personagens;

import Inimigos.Inimigos;

public class Guerreiro extends Personagem implements PersonagemInterface {
    private int danoCrit;

    public Guerreiro(){
        classeC = 'G';
        vida = 100;
        dano = 30;
        danoCrit = 0;
        level = 1;
        pts = 8;
        armadura = 20;

    }


    public void ataque1(Inimigos i){
        i.danoTomadoI(setDanoCrit());
    }

    public void ataque2(Inimigos i){

    }

    public int setDanoCrit(){
        int danoCrit = getDanoCrit();
        int sorte = r.nextInt(101);
        int dano = getDano();
        if(danoCrit > sorte){
            int danoFinal = dano * 2;
            System.out.println("Your character deals CRITICAL damage, dealing " + danoFinal + " damage");
            return danoFinal;
        }
        System.out.println("Your character deals " + dano +  " damage");
        return dano;
     }

     public int getDanoCrit() {
        return danoCrit;
    }
    
}
