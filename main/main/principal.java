package main;
import Funcoes.Funcoes;
import Inimigos.Inimigos;
import Personagens.Arqueira;
import Personagens.Personagem;
import Inimigos.Elemental;

public class principal {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        Arqueira a = new Arqueira();
        Personagem p = a;
        Elemental e = new Elemental(p);
        Inimigos i = e;
        i.inimigosArray();
    }
    
}
