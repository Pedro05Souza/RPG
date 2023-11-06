package Inimigos;
import Funcoes.Funcoes;
import Personagens.Personagem;

public class Golem extends Inimigos implements InimigosInterface {
    private boolean ativo, rodando = false;

    public Golem(Personagem p){
        super(p.getLevel());
        nome = "Golem";
        vida = 100;
        dano = 15;
        armadura = 35;
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
    public void ataque2(Personagem p){
        if(rodando){
            ataque1(p);
        } else {
        rodando = true;    
        int rodadas = Funcoes.rodadas;
        int rodadaFinal = rodadas + 3;
        int armadura = getArmadura();
        int armaduraMultiplicada = getArmadura() * 2;
        if(rodadas < rodadaFinal && ativo ==  false){
            System.out.println("Golem uses its ability: Stone Skin. It increases its armor by " + armaduraMultiplicada + " for 3 turns.");
            setArmadura(armaduraMultiplicada);
            ativo = true;
        } else if(rodadas < rodadaFinal && ativo == true){
            System.out.println("Golem's Stone Skin is still active.");
        } else {
            System.out.println("Golem's Stone Skin has ended.");
            rodando = false;
            ativo = false;
            setArmadura(armadura);
        }
    }
    }
     
    
    
    //Droplist (não tem itens)
    @Override
    public void dropList() {
    }
 


}
