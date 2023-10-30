package Personagens;
import Personagens.Personagem;
import Inimigos.Inimigos;

public interface PersonagemInterface {

    default void atacar(Inimigos i){
        System.out.println("--------------------------------");
        System.out.println("Choose your character's attacks: ");
        System.out.println("[1]. " + getAtaque1N());
        System.out.println("[2]. " + getAtaque2N());
    }

    void ataque1(Inimigos i);

    void ataque2(Inimigos i);

}
