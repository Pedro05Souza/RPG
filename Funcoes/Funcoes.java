package Funcoes;
import java.lang.reflect.InvocationTargetException;
import java.nio.Buffer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import Inimigos.*;
import Inventario.Inventario;
import Personagens.*;
import cores.cores;

public class Funcoes {
    public static int rodadas;
    private static Timer timer;
    private ArrayList<Inimigos> inimigos;
    private File save;
    private Scanner input;
    private Inimigos i;
    private Inventario inv;
    private Personagem p;

    public Funcoes(){
        timer = new Timer();
        input = new Scanner(System.in);
        i = null;
        inv = new Inventario();
        p = escolherClasse(); // falta resolver um bglh
        inimigos = new ArrayList<>();
        save = new File("save.txt");
    }

    // Função que salva os stats do jogador
    public void saveStats(){
        try (
        BufferedWriter writer = new BufferedWriter((new FileWriter(save)))) 
        {
        writer.write("Classe: " + p.imprimeClasse() + "\n");
        writer.write("Level: " + p.getLevel() + "\n");
        writer.write("XP: " + p.getXp() + "\n");
        writer.write("HP: " + p.getVida() + "\n");
        writer.write("Armor " + p.getArmadura() + "\n");
        writer.write("Damage: " + p.getDano() + "\n");
        writer.close();
        } catch (IOException e) {
            cores.setRed("An error occurred while saving.");
        }
        }

    // Função que carrega os stats do jogador
    public boolean loadStats() {
        boolean temvalor = false;
        try (
            BufferedReader reader = new BufferedReader((new FileReader(save)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String [] stats = line.split(": ");
                if(stats.length > 1){
                    String stat = stats[0];
                    String value = stats[1];
                switch (stat) {
                    case "Level":
                        p.setLevel(Integer.parseInt(value));
                        break;
                    case "XP":
                        p.setXp(Integer.parseInt(value));
                        break;
                    case "HP":
                        p.setVida(Integer.parseInt(value));
                        break;
                    case "Armor":
                        p.setArmadura(Integer.parseInt(value));
                        break;
                    case "Damage":  
                        p.setDano(Integer.parseInt(value));
                        break;
                    case "Classe":
                        // falta fazer essa merda tbm
                        break;
                
                    default:
                        break;
                }
            }
            }
            temvalor = true;
        } catch (IOException e) {
            cores.setRed("No save file was found.");
        }
        return temvalor;
    }

    public void loadSave(boolean temValor){
        if(temValor){
            cores.setGreen("The game detected a save file, would you like to load it?");
            System.out.println("[1] - Yes");
            System.out.println("[2] - No");
            int load = input.nextInt();
            switch (load) {
                case 1:
                cores.setGreen("Loading save...");
                loadStats();
                break;
                case 2:
                cores.setGreen("Starting new game...");
                escolherClasse();
                break;
                default:
                break;
            }
        }else {
            p = escolherClasse();
        }

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

    public void inimigosLista(){
        Golem golem = new Golem(p);
        Elemental elemental = new Elemental(p);
        inimigos.add(golem);
        inimigos.add(elemental);
        
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
                inimigosLista();
                i = Inimigos.inimigoEscolhido(inimigos);
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
                saveStats();
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
        cores.setYellow("Main Menu");
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
