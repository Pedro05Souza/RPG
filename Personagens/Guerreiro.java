package Personagens;
import Inimigos.Inimigos;

/*
 * Classe da personagem guerreiro
 */
public class Guerreiro extends Personagem implements PersonagemInterface {
    private int critDmg, sorteAtual;
    
    public Guerreiro(){
        classeC = 'G';
        vidaMax = 100;
        vida = vidaMax;
        dano = 30;
        critDmg = 2;
        level = 1;
        pts = 8;
        armadura = 10;
        sorteAtual = 10;
        setAtaque1N("Warrior's Slash");
        setAtaque2N("Celestial Judgement");
    }
    

    //Método que seleciona o tipo de ataque
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

    //Executa o primeiro tipo de ataque
    @Override
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

    //Executa o segundo tipo de ataque
    @Override
    public void ataque2(Inimigos i) {
        int danoMultiplicado =  getDano() * 2;
        int sorteAtual = getSorteAtual();
        int rng = (int) (Math.random() * 100);
        System.out.println(sorteAtual);
        if(sorteAtual > rng){
            System.out.println("Your attack reflects back to you, dealing " + danoTomadoP(danoMultiplicado) + " damage");
            sorteAtual = 10;
        } else {
            setSorteAtual(sorteAtual += 5);
            System.out.println("Your attack deals: " + i.danoTomadoI(danoMultiplicado) + " damage.");
            System.out.println("Chance to reflect back to you: " + sorteAtual + "%");
        }

    }

    // getters and setters
     public int getDanoCrit() {
        return critDmg;
    }

    public int getSorteAtual() {
        return sorteAtual;
    }

    public void setSorteAtual(int sorteAtual) {
        this.sorteAtual = sorteAtual;
    }

    
    
}
