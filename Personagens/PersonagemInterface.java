package Personagens;
import Personagens.Personagem;
import Inimigos.Inimigos;

public interface PersonagemInterface {

    default void atacar(Inimigos i){
        Personagem.menuAtaque();
    }
    void ataque1(Inimigos i);

    void ataque2(Inimigos i);

}
