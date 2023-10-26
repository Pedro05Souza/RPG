package Personagens;
import Inimigos.Inimigos;

public interface PersonagemInterface {

    default void atacar(Inimigos i){
        System.out.println("--------------------------------");
        System.out.println("Choose your character's attacks: ");
        System.out.println("[1]. attack here ");
        System.out.println("[2]. Attack 2 here");
    }

}
