package Personagens.Classes;
import java.util.TimerTask;
import Inimigos.Inimigos;
import Personagens.Personagem;
import Personagens.PersonagemInterface;

/*
 * Classe da personagem Knight
 */
public class Knight extends Personagem implements PersonagemInterface {
    private final int statusIncrease = 40;
    private int cooldown;


    public Knight(){
        classeCaractere = 'K';
        dano = 20;
        vidaMax = 100;
        vida = vidaMax;
        level = 1;
        pts = 8;
        armadura = 30;
        cooldown = 3;
        setAtaque1N("Sword Slash");
        setAtaque2N("Eternal Vow;");
        }

    //Seleciona o tipo de ataque
    @Override
    public void atacar(Inimigos i){
        PersonagemInterface.super.atacar(i);
        int menu = input.nextInt();
        switch(menu){
            case 1:
            ataque1(i);
            break;
            case 2:
            ataque2(i);
            break;
        }
    }

    //Executa o primeiro tipo de ataque
    @Override
    public void ataque1(Inimigos i){
        int dano = getDano();
        System.out.println("Your character deals " + i.danoTomadoI(dano) +  " damage to " + i.getNome());
    }

    //Executa o segundo tipo de ataque
    @Override
    public void ataque2(Inimigos i){
        int rng = (int) (Math.random() * 100);
        int dano = getDano();
        if(statusIncrease >= rng){
            aumentoStatus();
        }
        else {
            int danoDim = dano / 2;
            System.out.println("Your character failed but deals half of the damage instead. Damage: " + i.danoTomadoI(danoDim));
        }
    }
    
    // Aumenta o status da classe Knight por um tempo
    public void aumentoStatus(){
        int danoOriginal = getDano();
        int armaduraOriginal = getArmadura();
        int danoAumentado =  (int) (danoOriginal * 0.15);
        int armaduraAumentado = (int) (armaduraOriginal * 0.3);
        setArmadura(armaduraAumentado);
        setDano(danoAumentado);
        int tempoDuracao = cooldown * 1000;
        System.out.println("Your character increases the statues of his sword and armor, increasing his damage by " + danoAumentado + " and armor by " + armaduraAumentado + " for " + cooldown + " seconds.");
        setDano(danoAumentado);
        setArmadura(armaduraAumentado);
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                System.out.println("Your character's sword and armor returns to normal.");
                setDano(danoOriginal);
                setArmadura(armaduraOriginal);

            }
        }, tempoDuracao);

    }




}
