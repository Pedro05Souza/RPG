package Personagens;

import Inimigos.Inimigos;

public class Guerreiro extends Personagem implements PersonagemInterface {
    private int danoCrit, sorteAtual;

    public Guerreiro(){
        classeC = 'G';
        vida = 100;
        dano = 30;
        danoCrit = 2;
        level = 1;
        pts = 8;
        armadura = 20;
        sorteAtual = 10;
    }

    @Override
    public void atacar(Inimigos i){
        PersonagemInterface.super.atacar(i);
        int menu = input.nextInt();
        switch(menu){
            case 1:
            ataque1(i);
            break;
            case 2:
            ataque2(i);
            break;
        }
    }


    public void ataque1(Inimigos i){
        int danoCrit = getDanoCrit();
        int sorte = r.nextInt(101);
        int dano = getDano();
        if(danoCrit > sorte){
            int danoFinal = dano * 2;
            System.out.println("Your character deals CRITICAL damage, dealing " + danoFinal + " damage to " + i.getNome());
        }
        System.out.println("Your character deals " + dano +  " damage to " + i.getNome());
    }


    public void ataque2(Inimigos i){
        int danoMultiplicado =  getDano() * 2;
        int sorteAtual = getSorteAtual();
        int sorte = r.nextInt(101);
        if(sorteAtual > sorte){
            System.out.println("Your attack reflects back to you, dealing " + danoTomadoP(danoMultiplicado) + " damage");
            sorteAtual = 10;
        } else {
            setSorteAtual(sorteAtual += 5);
            int porcentagem = (sorteAtual / sorte) * 10;
            System.out.println("Your attack deals: " + i.danoTomadoI(danoMultiplicado) + " damage.");
            System.out.println("Chance to reflect back to you: " + porcentagem + "%");
        }

    }

    // getters and setters
     public int getDanoCrit() {
        return danoCrit;
    }

    public int getSorteAtual() {
        return sorteAtual;
    }

    public void setSorteAtual(int sorteAtual) {
        this.sorteAtual = sorteAtual;
    }

    
    
}
