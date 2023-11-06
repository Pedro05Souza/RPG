package Inimigos;
import Funcoes.Funcoes;
import Personagens.Personagem;

public class Golem extends Inimigos implements InimigosInterface {
    private boolean rodando = false;

    public Golem(Personagem p){
        super(p.getLevel());
        nome = "Golem";
        vida = 100000;
        dano = 1;
        armadura = 2;
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
        if(rodando){
            ataque1(p);
        }
            int rodadas = Funcoes.rodadas;
            int rodadasFinal = rodadas + 3;
            System.out.println("Picles: " + rodadasFinal);
            if(rodadas < rodadasFinal){
                System.out.println("Golem uses stone skin. It's defense is multiplied by 2");
                setArmadura(getArmadura() * 2);
                rodando = true;
            } else {
                System.out.println("Golem's stone skin wears off. It's defense returns to normal");
                setArmadura(getArmadura() / 2);
                rodando = false;
            }
        }
     
    
    
    //Droplist (não tem itens)
    @Override
    public void dropList() {
    }
 


}
