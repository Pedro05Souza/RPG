package Funcoes;

import java.lang.reflect.InvocationTargetException;
import Inimigos.Inimigos;
import Personagens.Arqueira;
import Personagens.Guerreiro;
import Personagens.Knight;
import Personagens.Mago;
import Personagens.Personagem;
import Inventario.Inventario;

import java.util.Scanner;

public class Funcoes {
    public static int Rodadas;
    private Scanner input = new Scanner(System.in);
    private Inimigos i = null;
    private Knight k = new Knight();
    private Mago m = new Mago();
    private Guerreiro g = new Guerreiro();
    private Arqueira a = new Arqueira();
    private Inventario inv = new Inventario();
    Personagem p = escolherClasse();


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
                p = a;
            break;
            default:
                System.out.println("Invalid option.");
                break;
        }
        p.getClasse(p);
        return p;
    }
    
    public void menuPrincipal() throws NoSuchMethodException, IllegalAccessException{
        System.out.println("--------------------------------");
        System.out.println("Main Menu");
        System.out.println("[1]. Battle Enemies");
        System.out.println("[2]. Inventory");
        System.out.println("[3]. Character");
        System.out.println("[4]. Skill Tree");
        System.out.println("[5]. Shop");
        System.out.println("[6]. Save and leave");
        System.out.println("--------------------------------");
        int menu = input.nextInt();
        switch (menu) {
            case 1:
            batalha(i.inimigoEscolhido(i.inimigosArray()), p); 
            break;
            case 2:
            inv.mostrarItens();
            break;
            case 3:
            p.status();
            case 4:
            p.setAtributos(p);
            break;
            case 5:
            // falta fazer a loja
            break;
            case 6:
            System.out.println("Game saved.");
            break;
            default:
            break;
        }


    }

    
}
