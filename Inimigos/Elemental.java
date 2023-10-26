package Inimigos;
import Personagens.Personagem;

public class Elemental extends Inimigos implements InimigosInterface {
    public static int ai;

        public Elemental(Personagem p){
            super(p.getLevel());
            nome = "Shadow Elemental";
            vida = 10000;
            dano = 1;
            armadura = 2;
            nivel = 1;
        }


    @Override
    public void atacar(Personagem p) {
        int ai = random.nextInt(2);
        switch(ai){
            case 0:
            ataque1(p);
            break;
            case 1:
            ataque2(p);
            break;
        } 
    }

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

    public void ataque2(Personagem p){
        int dmg = dano;
        if(p.getVida() > 0){
            System.out.println("Shadow Elemental releases one of its abilities: Dark Energy. It deals " + p.danoTomadoP(dmg) + " damage.");

        }
    }

    
    
}
