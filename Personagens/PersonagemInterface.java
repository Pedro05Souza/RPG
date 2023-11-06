package Personagens;
import Inimigos.Inimigos;

/*
 * Métodos para todos os personagens
 */
public interface PersonagemInterface {

    default void atacar(Inimigos i){
        Personagem.menuAtaque();
    }
    void ataque1(Inimigos i);

    void ataque2(Inimigos i);

}
