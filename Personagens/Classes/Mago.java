package Personagens.Classes;
import java.util.*;

import Inimigos.Inimigos;
import Personagens.Personagem;
import Personagens.PersonagemInterface;

/*
 * Classe da personagem Mago
 */

public class Mago extends Personagem implements PersonagemInterface {
    private int mana, manaTotal, wisdom;

    public Mago(){
        classeCaractere = 'M';
        vidaMax = 70;
        vida = vidaMax;
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

    //Seleciona o tipo de ataque
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
            case 3:
            if(fugirBatalha()){
                break;
            }
            break;
        }
        
    }
    //Executa o primeiro tipo de ataque
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
        if(mana >= manaNecessaria(.5)){
            int dano = getDano();
            int fogoDuracao = 5000;
            int danoFogo = (int) Math.sqrt(dano);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    while (System.currentTimeMillis() < fogoDuracao) {
                    System.out.println("Teste");
                    i.danoTomadoI(danoFogo);
                    }
                }
            }, fogoDuracao);
              reducaoMana(.5);  
            };
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
