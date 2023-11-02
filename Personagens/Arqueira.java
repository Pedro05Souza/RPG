package Personagens;

import java.util.TimerTask;

import Inimigos.Inimigos;

public class Arqueira extends Personagem implements PersonagemInterface{
    private int ArrowsA, Arrows;

    public Arqueira(){
        classeC = 'A';
        vida = 70;
        dano = 50;
        Arrows = 10;
        ArrowsA = Arrows;
        level = 1;
        pts = 8;
        armadura = 5;
    }

    {
        setAtaque1N("Arrow Shot");
        setAtaque2N("Arrow Rain");
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
            ataque2(i);
            break;
        }
    }

    @Override
    public void ataque1(Inimigos i) {
        int dano = getDano();
        if(Arrows > 0){
            System.out.println("Your attack deals: " + i.danoTomadoI(dano) + " damage to " + i.getNome());
            Arrows--;
        } else {
            recarregarFlechas();
            System.out.println("Insufficient arrows!");
        }
    }

    @Override
    public void ataque2(Inimigos i) {
        int dano = getDano();
        if(ArrowsA >= 5){
            int flechasRng = r.nextInt(getNumFlechas());
            int danoTotal = dano * flechasRng;
            System.out.println("Your character releases an arrow rain. Dealing " + i.danoTomadoI(dano) + " damage to " + i.getNome());
            ArrowsA -= flechasRng;
        } else {
            recarregarFlechas();
            System.out.println("Insufficient arrows!");
        }
    }

    public void recarregarFlechas(){
        int tempoDuracao = 5000;
        if(ArrowsA < Arrows){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Your character reloaded an arrow...");
                    ArrowsA++;
                }
            }, tempoDuracao);
            };
        }


    // getters e setters
    public int getNumFlechas() {
        return Arrows;
    }

    public void setNumFlechas(int numFlechas) {
        Arrows = numFlechas;
    }

    
    
}
