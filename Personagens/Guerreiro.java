package Personagens;

import Inimigos.Inimigos;

public class Guerreiro extends Personagem implements PersonagemInterface {
    private int danoCrit;

    public Guerreiro(){
        classeC = 'G';
        vida = 100;
        dano = 30;
        level = 1;
        pts = 8;
        armadura = 20;

    }


    public void ataque1(Inimigos i){
        int dano = getDano();
        i.danoTomadoI(dano);
    }

    public int getDanoCrit() {
        return danoCrit;
    }

    public void setDanoCrit(int danoCrit) {
        this.danoCrit = danoCrit;
    }

    
    
}
