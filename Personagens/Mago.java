package Personagens;
import java.util.Timer;
import java.util.TimerTask;

import Inimigos.Inimigos;


public class Mago extends Personagem implements PersonagemInterface {
    private int mana, manaTotal, wisdom;
    
    {
        setAtaque1N("Fireball");
        setAtaque2N("Fire Damage Spell");
    }

    public Mago(){
        classeC = 'M';
        vida = 70;
        dano = 50;
        manaTotal = 100;
        mana = manaTotal;
        level = 1;
        pts = 8;
        armadura = 5; 
        wisdom = 1;
    }

  
    @Override
    public void atacar(Inimigos i) {
        PersonagemInterface.super.atacar(i);
        int menu = input.nextInt();
        switch(menu){
            case 1:
            ataque1(i);
            break;
            case 2:
            //ataque2(i);
            break;
        }
        
    }
    @Override
    public void ataque1(Inimigos i) {
        int dano = getDano();
        if(mana >= manaNecessaria(.2)){
            System.out.println("Your attack deals: " + i.danoTomadoI(dano) + " damage to " + i.getNome());
           reducaoMana(.2);
        } else {
            System.out.println("Insufficient mana!");
        }
    }

    @Override
    public void ataque2(Inimigos i){
       int dano = getDano();
    if (mana >= manaNecessaria(0.5)){
        System.out.println("Your character releases fire damage spell. Dealing " + dano + " damage to " + i.getNome());
        int duracaoAtaque = 10000; 
        int intervaloDano = 1000; 
        int danoFogo = (int) Math.sqrt(dano);
        int danoFinal = Math.max(danoFogo, 1);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            long endTime = System.currentTimeMillis() + duracaoAtaque;
            @Override
            public void run() {
                while (System.currentTimeMillis() < endTime) {
                    i.danoTomadoI(danoFinal);
                    try {
                        Thread.sleep(intervaloDano);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, intervaloDano);
        reducaoMana(0.5);

    } else {
        System.out.println("Insufficient mana");
    }
    
    }

    // Funções que cuidam da mana

    public void reducaoMana(double porcentagem){
        int valorReduzido = (int) Math.max(((int) mana * porcentagem), 0);
        setMana(getMana() - valorReduzido);
        System.out.println("Your mana has been reduced to:" + getMana());
    }

    public int manaNecessaria(double percentual){
        int manaN = (int) (manaTotal * percentual);
        return manaN;
    }


    // Setters e Getters

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    
    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

     public int getManaTotal() {
        return manaTotal;
    }


    public void setManaTotal(int manaTotal) {
        this.manaTotal = manaTotal;
    }

    
    
}
