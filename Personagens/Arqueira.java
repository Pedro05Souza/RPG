package Personagens;
import java.util.TimerTask;
import Inimigos.Inimigos;

/*
 * Classe da personagem arqueira
 */
public class Arqueira extends Personagem implements PersonagemInterface{
    private int ArrowsD, Arrows;

    public Arqueira() {
        classeC = 'A';
        vida = 70;
        dano = 50;
        Arrows = 10;
        ArrowsD = Arrows;
        level = 1;
        pts = 8;
        armadura = 5;
        setAtaque1N("Arrow Shot");
        setAtaque2N("Arrow Rain");
    }

    //Método para seleção de ataque
    @Override
    public void atacar(Inimigos i) {
        recarregarFlechas();
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
    public void ataque1(Inimigos i) {
        int dano = getDano();
        System.out.println(ArrowsD);
        if(ArrowsD > 0){
            System.out.println("Your attack deals: " + i.danoTomadoI(dano) + " damage to " + i.getNome());
            ArrowsD--;
        } else {
            System.out.println("Insufficient arrows!");
        }
    }

    //Executa o segundo tipo de ataque
    @Override
    public void ataque2(Inimigos i) {
        int dano = getDano();
        System.out.println(ArrowsD);
        if(ArrowsD >= 5){
            int flechasRng = r.nextInt(getNumFlechas());
            int danoTotal = dano * flechasRng;
            System.out.println("Your character releases an arrow rain. Dealing " + i.danoTomadoI(danoTotal) + " damage to " + i.getNome());
            ArrowsD -= flechasRng;
        } else {
            System.out.println("Insufficient arrows!");
        }
    }
    
    //Recarrega as flechas da arqueira
    public void recarregarFlechas(){
        int tempoDuracao = 3000;
        if (ArrowsD < Arrows){
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Your character reloaded an arrow...");
                    ArrowsD++;
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
