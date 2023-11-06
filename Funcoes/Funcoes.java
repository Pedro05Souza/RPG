package Funcoes;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.*;
import java.util.*;
import Inimigos.*;
import Inventario.Inventario;
import Personagens.*;
import cores.cores;

public class Funcoes {
    public static int rodadas;
    private static Timer timer;
    private Scanner input;
    private Inimigos i;
    private Inventario inv;
    private Personagem p;

    public Funcoes(){
        timer = new Timer();
        input = new Scanner(System.in);
        i = null;
        inv = new Inventario();
        p = escolherClasse();
    }


    // Função que faz as lutas entre os Personagens e Inimigos
    public void batalha(Inimigos i, Personagem p) throws NoSuchMethodException, IllegalAccessException{
        i.setAtributosInimigo();
        i.imprimeInimigoBatalha();
        while(p.getVida() > 0 && i.getVida() > 0){
            try {
                System.out.println("Round: " + rodadas);
                p.getAtaque(p, i);
                i.getAtaque(i, p);
                hpIP(i, p);
                rodadas++;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        limpaConsole();
        i.death(p);
        p.death();
        rodadas = 0;
    }
    // Função que mostra o HP do jogador e do inimigo
    public void hpIP(Inimigos i, Personagem p){
        p.hpJogador();
        i.hpInimigo();
       
    }

   

    // Função que limpa o console a cada 4 segundos
    public static void limpaConsole(){
        int delay = 4000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
            
        }, delay);
    }

    // Função que adiciona os inimigos na lista
    public List<Inimigos> inimigosLista(List<?> inimigos){
        return inimigos.stream()
                .filter(i -> i instanceof Inimigos)
                .map(i -> (Inimigos) i)
                .collect(Collectors.toList());
    }

    // Função que escolhe a classe do jogador
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
                Knight k = new Knight();
                p = k;
                break;
            case 2:
                Mago m = new Mago();
                p = m;
                break;
            case 3:
                Guerreiro g = new Guerreiro();
                p = g;
                break;
            case 4:
                Arqueira a = new Arqueira();
                p = a;
            break;
            default:
                System.out.println("Invalid option.");
                break;
        }
        cores.setGreen("You have successfully chosen your class.");
        p.getClasse(p);
        return p;
    }
    // Função que mostra o menu principal    
    public void menuPrincipal() throws NoSuchMethodException, IllegalAccessException{
        boolean running = true;
        while(running){
            int menu = menu();
            switch (menu) {
                case 1:
                Golem g = new Golem(p);
                i = g;
                batalha(i, p);
                break;
                case 2:
                inv.menuInventario();
                break;
                case 3:
                p.status();
                break;
                case 4:
                p.setAtributos(p);
                break;
                case 5:
                // falta fazer a loja
                break;
                case 6:
                cores.setGreen("Saving and leaving.");
                running = false;
                System.exit(0);
                break;
                default:
                break;
            }
        }


    }

    public int menu(){
        System.out.println("--------------------------------");
        System.out.println("Main Menu");
        System.out.println("[1]. Battle Enemies");
        System.out.println("[2]. Inventory");
        System.out.println("[3]. Character");
        System.out.println("[4]. Skill Tree");
        System.out.println("[5]. Shop");
        System.out.println("[6]. Save and leave");
        System.out.println("--------------------------------");
        return input.nextInt();
    }
}
