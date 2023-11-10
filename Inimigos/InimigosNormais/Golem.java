package Inimigos.InimigosNormais;
import Inimigos.Inimigos;
import Inimigos.InimigosInterface;
import Inventario.Equipamentos.WarriorSword;
import Personagens.Personagem;

public class Golem extends Inimigos implements InimigosInterface {

    public Golem(Personagem p){
        super(p.getLevel());
        nome = "Golem";
        vidaMax = 150;
        vida = vidaMax;
        dano = 1;
        armadura = 2;
        xp = 100;
        nivel = 5;
        dropList();
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

    }
     
    
    
    //Droplist (não tem itens)
    @Override
    public void dropList() {
        drops.add(new WarriorSword());
    }
 
}
