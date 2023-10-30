package Personagens;

import java.util.Timer;
import java.util.TimerTask;

import Inimigos.Inimigos;

public class Knight extends Personagem implements PersonagemInterface {
    private int statusIncrease;

    public Knight(){
        classeC = 'K';
        dano = 20;
        vida = 100;
        level = 1;
        pts = 8;
        statusIncrease = 5;
        armadura = 30;
    }

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

    @Override
    public void ataque1(Inimigos i){
        setAtaque1N("Sword Slash;");
        int dano = getDano();
        System.out.println("Your character deals " + dano +  " damage to " + i.getNome());
    }
    @Override
    public void ataque2(Inimigos i){
        setAtaque2N("Status Increase Vow;");
        int rng = (int) (Math.random() * 100);
        int dano = getDano();
        if(statusIncrease >= rng){
            aumentoStatus();
        }
        else {
            int danoDim = dano / 2;
            System.out.println("Your character failed to stun " + i.getNome() + " But deals half of the damage instead. Damage: " + i.danoTomadoI(danoDim));
        }
    }
    public void aumentoStatus(){
        int danoOriginal = getDano();
        int armaduraOriginal = getArmadura();
        int danoAumentado =  (int) (danoOriginal * 0.15);
        int armaduraAumentado = (int) (armaduraOriginal * 0.3);
        setArmadura(armaduraAumentado);
        setDano(danoAumentado);
        int tempoDuracao = 5000;
        System.out.println("Your character increases the statues of his sword and armor, increasing his damage by " + danoAumentado + " and armor by " + armaduraAumentado + " for 5 seconds.");
        Timer timer = new Timer();
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
