package Personagens;
import java.util.Timer;
import java.util.TimerTask;

import Inimigos.Inimigos;


public class Mago extends Personagem implements PersonagemInterface {
    private int mana, manaTotal, wisdom;

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
        setAtaque1N("Fireball");
        setAtaque2N("Fire Damage Spell");
    }

  
    @Override
    public void atacar(Inimigos i) {
        PersonagemInterface.super.atacar(i);
        int menu = input.nextInt();
        manaRegen();
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

    //Falta fazer (Todo errado)
    @Override
    public void ataque2(Inimigos i){

    }

    // Funções que cuidam da mana

    public void manaRegen(){
        int tempoDuracao = 3000;
        if (mana < manaTotal){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Your character regenerated mana...");
                    setMana(getMana() + 1);
                }
            }, tempoDuracao);
            };
        }

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
