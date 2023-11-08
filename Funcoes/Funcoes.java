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
import Inimigos.InimigosNormais.Elemental;
import Inimigos.InimigosNormais.Golem;
import Inventario.Inventario;
import Personagens.*;
import Personagens.Classes.Arqueira;
import Personagens.Classes.Guerreiro;
import Personagens.Classes.Knight;
import Personagens.Classes.Mago;
import cores.cores;

/*
 * Classe que contém as funções do jogo
 * STATUS: Funcional, ajustes no load e save;
 */

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
        p = null;
        inimigos = new ArrayList<>();
        save = new File("save.txt");
    }

    // Função que salva os stats do jogador
    public void saveStats(){
        try (
        BufferedWriter writer = new BufferedWriter((new FileWriter(save)))) 
        {
        writer.write(encryptS("Class") + ": " + encryptS(p.imprimeClasse()) + "\n");
        writer.write(encryptS("Level") + ": " + encryptI(p.getLevel()) + "\n");
        writer.write(encryptS("XP") + ": " + encryptI(p.getXp()) + "\n");
        writer.write(encryptS("HP")+ ": " + encryptI(p.getVida()) + "\n");
        writer.write(encryptS("Armor") + ": " + encryptI(p.getArmadura()) + "\n");
        writer.write(encryptS("Damage")+ ": " + encryptI(p.getDano()) + "\n");
        writer.close();
        } catch (IOException e) {
            cores.setRed("An error occurred while saving.");
        }
    }

    // Função que criptografa os stats do jogador
    public String encryptS(String bayo) {
        String palavra = "N/A";
        StringBuilder sb = new StringBuilder();
         char [] array = bayo.toCharArray();
         for(int i = 0; i < array.length; i++){
             array[i] += 5;
         }
         palavra = sb.append(array).toString();
         return palavra;
    }

    // Função que descriptografa os stats do jogador
    public String decryptS(String bayo){
        String palavra = "N/A";
        StringBuilder sb = new StringBuilder();
        char [] array = bayo.toCharArray();
        for(int i = 0; i < array.length; i++){
            array[i] -= 5;
        }
        palavra = sb.append(array).toString();
        return palavra;
    }
    
    // Função que criptografa os stats do jogador
    public String encryptI(int bayo) {
        String s = Integer.toString(bayo);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            switch (c) {
                case '1':
                    sb.append("a");
                    break;
                case '2':
                    sb.append("b");
                    break;
                case '3':
                    sb.append("c");
                    break;
                case '4':
                    sb.append("d");
                    break;
                case '5':
                    sb.append("e");
                    break;
                case '6':
                    sb.append("f");
                    break;
                case '7':
                    sb.append("g");
                    break;
                case '8':
                    sb.append("h");
                    break;
                case '9':
                    sb.append("i");
                    break;
                case '0':
                    sb.append("j");
                    break;
                default:
                    break;
            }
        }
        s = sb.toString();
        return s;
        
    }
    // Função que descriptografa os stats do jogador
    public int decryptI(String bayo){
        int i = 0;
        StringBuilder sb = new StringBuilder();
        char [] array = bayo.toCharArray();
        for(int j = 0; j < array.length; j++){
            char c = array[j];
            switch (c) {
                case 'a':
                    sb.append("1");
                    break;
                case 'b':
                    sb.append("2");
                    break;
                case 'c':
                    sb.append("3");
                    break;
                case 'd':
                    sb.append("4");
                    break;
                case 'e':
                    sb.append("5");
                    break;
                case 'f':
                    sb.append("6");
                    break;
                case 'g':
                    sb.append("7");
                    break;
                case 'h':
                    sb.append("8");
                    break;
                case 'i':
                    sb.append("9");
                    break;
                case 'j':
                    sb.append("0");
                    break;
                default:
                    break;
            }
        }
        i = Integer.parseInt(sb.toString());
        return i;
    }

    public File descriptador(File save){
        try(
            BufferedReader reader = new BufferedReader((new FileReader(save)))){
            String line;
            while((line = reader.readLine()) != null){
                String [] stats = line.split(": ");
                if(stats.length > 1){}
            }

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
                    case "Class":
                        switch (value) {
                            case "Knight":
                                p = new Knight();
                                break;
                            case "Arqueira":
                                p = new Arqueira();
                                break;
                            case "Guerreiro":
                                p = new Guerreiro();
                                break;
                            case "Mago":
                                p = new Mago();
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
            }
            temvalor = true;
        } catch (IOException | NullPointerException e) {
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
                cores.setRed("Invalid option.");
                break;
            }
        }else {
            p = escolherClasse();
        }

    }

    public void deleteSave(){
        if(save.exists() ==  false){
            cores.setRed("No save file was found.");
        } else {
            save.delete();
            cores.setGreen("Save file deleted.");
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
        i.death(p);
        p.death(this);
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
        cores.setGreen("Your journey begins here, choose your class.");
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
                case 7:
                deleteSave();
                break;
                default:
                cores.setRed("Invalid option.");
                break;
            }
        }


    }

    public int menu(){
        System.out.println("--------------------------------");
        cores.setYellow("Main Menu");
        System.out.println("[1]. Battle Enemies;");
        System.out.println("[2]. Inventory;");
        System.out.println("[3]. Character;");
        System.out.println("[4]. Skill Tree;");
        System.out.println("[5]. Shop;");
        System.out.println("[6]. Save and leave;");
        System.out.println("[7]. Delete Save;");
        System.out.println("--------------------------------");
        return input.nextInt();
    }

    // getters e setters
    public File getSave() {
        return save;
    }
}
