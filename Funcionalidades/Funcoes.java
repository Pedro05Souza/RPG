
import java.lang.reflect.InvocationTargetException;

import Inimigos.Elemental;
import Inimigos.Inimigos;
import Personagens.Guerreiro;
import Personagens.Knight;
import Personagens.Mago;
import Personagens.Personagem;

import java.util.Scanner;

public class Funcoes {
    public static int Rodadas;
    private Scanner input = new Scanner(System.in);
    private Knight k = new Knight();
    private Mago m = new Mago();
    private Guerreiro g = new Guerreiro();


    // Função que faz as lutas entre os Personagens e Inimigos
    public void batalha(Inimigos i, Personagem p) throws NoSuchMethodException, IllegalAccessException{
        while(p.getVida() > 0 && i.getVida() > 0){
            try {
                Rodadas++;
                p.getAtaque(p, i);
                i.getAtaque(i, p);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        Rodadas = 0;
    }

    public Personagem escolherClasse(){
        Personagem p = null;
        System.out.println("Your journey is about to begin, pick your class: ");
        System.out.println("[1] - Knight");
        System.out.println("[2] - Mage");
        System.out.println("[3] - Warrior");
        System.out.println("[4] - Archer");
        int classe = input.nextInt();
        switch (classe) {
            case 1:
                p = k;
                break;
            case 2:
                p = m;
                break;
            case 3:
                p = g;
                break;
            case 4:
            // falta fazer a clase Arqueira;
            break;
            default:
                System.out.println("Invalid option.");
                break;
        }
        return p;
    }
    
    public void menuPrincipal(){
        System.out.println("--------------------------------");
        System.out.println("Main Menu");
        System.out.println("[1]. Battle Enemies");
        System.out.println("[2]. Inventory");
        System.out.println("[3]. Character");
        System.out.println("[4]. Shop");
        System.out.println("[5]. Save and leave");
        System.out.println("--------------------------------");
        int menu = input.nextInt();
        switch (menu) {
            case 1:
                break;
        
            default:
                break;
        }


    }

    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException {
        Knight g = new Knight();
        Personagem p = g;
        Elemental e = new Elemental(g);
        Funcoes f = new Funcoes();
         try{
            f.batalha(e, p);
        }catch(IllegalArgumentException | IllegalAccessException i){
            System.out.println(i);

        }
    }
}
