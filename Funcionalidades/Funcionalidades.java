import java.lang.reflect.InvocationTargetException;
import Inimigos.Inimigos;
import Personagens.Personagem;

public class Funcionalidades{

    public void batalha(Inimigos i, Personagem p) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        while(p.getVida() > 0 || i.getVida() > 0){
            p.getAtaque(p, i);
            i.getAtaque(i, p);
        }
    }

} 