package Inimigos;

import java.util.TimerTask;

import Personagens.Personagem;

public class Golem extends Inimigos implements InimigosInterface {

    public Golem(Personagem p){
        super(p.getLevel());
        nome = "Golem";
        vida = 100;
        dano = 15;
        armadura = 35;
        xp = 100;
        nivel = 5;
    }

    //Escolhe aleatoriamente um ataque do inimigo
    @Override
    public void atacar(Personagem p) {
        ai = random.nextInt(2);
        switch(ai){
            case 0:
            ataque1(p);
            break;
            case 1:
            ataque2(p);
            break;
        }
    }

    //Executa o primeiro tipo de ataque do inimigo
    @Override
    public void ataque1(Personagem p) {
        int dano = getDano();
        System.out.println("Golem attacks you with its fists. Dealing " + p.danoTomadoP(dano) + " damage.");
        
        
    }

    //Executa o segundo tipo de ataque do inimigo
    @Override
    public void ataque2(Personagem p) {
       int armadura = getArmadura();
       int armaduraMultiplicada = getArmadura() * 2;
       int tempo = 4000;
       System.out.println("Golem uses its ability: Stone Skin. It increases its armor by " + armaduraMultiplicada + " for 4 seconds.");
       setArmadura(armaduraMultiplicada);
       timer.schedule(new TimerTask() {
              @Override
              public void run() {
                System.out.println("Golem's Stone Skin has ended.");
                setArmadura(armadura);
              }
         }, tempo);
       }
    
    
    //Droplist (não tem itens)
    @Override
    public void dropList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropList'");
    }

    
}
