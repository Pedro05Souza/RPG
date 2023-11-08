package Inimigos.InimigosNormais;
import Inimigos.Inimigos;
import Inimigos.InimigosInterface;
import Personagens.Personagem;
public class Elemental extends Inimigos implements InimigosInterface {

    public Elemental(Personagem p){
        super(p.getLevel());
        nome = "Shadow Elemental";
        vidaMax = 100;
        vida = vidaMax;
        dano = 40;
        armadura = 15;
        xp = 100;
        nivel = 2;
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
        int menosQueMetade = (int) ( vida * 0.3 );
        if(p.getVida() > 0 && vida <= menosQueMetade){
            int vidaSugada = (int) (p.getVida() * 0.3);
             setVida(getVida() + vidaSugada);
             System.out.println("Shadow Elemental unleashes one of its ominous abilities: Drain Health. It has drained " + vidaSugada + " of your health.");
        } else {
            ataque2(p);
        }
    }

    //Executa o segunto tipo de ataque do inimigo
    @Override
    public void ataque2(Personagem p){
        int dmg = dano;
        if(p.getVida() > 0){
            System.out.println("Shadow Elemental releases one of its abilities: Dark Energy. It deals " + p.danoTomadoP(dmg) + " damage.");

        }
    }

    //Droplist (não tem itens ainda)
    @Override
    public void dropList() {
        
    }



    
    
}
