package Inventario;
import Personagens.Personagem;
import cores.cores;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Classe de inventário do personagem
 * STATUS: falta testar todos os métodos;
 */
public class Inventario {
    private static final int MAXITENS = 5;
    private static final int MAXITENSEQUIPADOS = 3;
    private Personagem p;
    private Scanner input;
    protected int posAtual;
    ArrayList<Item> itens;
    ArrayList<Item> itensEquipados;

    public Inventario(){
        itens = new ArrayList<Item>(MAXITENS);
        itensEquipados = new ArrayList<Item>(MAXITENSEQUIPADOS);
        posAtual = -1;
        input = new Scanner(System.in);
    }
    
    // metodo que adiciona itens
    public void adicionarItem(Item i){
        if(itens.size() >= MAXITENS){
            cores.setRed("Inventory is full");
        }

        for(Item item : itens){
            if(item.getNome() == i.getNome()){
                cores.setGreen("You already have this item. Money was given instead.");
                return;
            }
            else {
            itens.add(i);
            posAtual = itens.size();
            cores.setGreen(i.getNome() + "added to inventory.");
        }
        }
    }

    // metodo que remove itens
    public void removerItem(int posicao){
        if(posicao > 5 || posicao < 0){
            cores.setRed("Invalid Position");
        }
        for (Item item : itens) {
            if (itens.indexOf(item) == posicao) {
                itens.remove(item);
                posAtual = itens.size();
                cores.setGreen(item.getNome() + " removed from inventory.");
                break;
            }
        }
    }

    // metodo que mostra os itens
    public void mostrarItens(){
            for(Item i : itens){
               i.efeitosItem();
            }
        }

    // metodo que pega os itens do Inimigo que morreu
    public void pegaritemInimigo(Item i){
        if(i != null){
            System.out.println("You just dropped a " + i.getNome() + "!");
            System.out.println("Rarity: " + i.getRaridadeString());
            System.out.println("[1]. Pick up;");
            System.out.println("[2]. Leave it;");
            int opcao = input.nextInt();
            switch(opcao){
                case 1:
                adicionarItem(i);
                break;
                case 2:
                System.out.println("You left the item.");
                break;
            }
        }
    }

    // metodo que retorna o objeto no indice que o usuario digitar
    public Item getItem(int posicao){
        Item i = null;
        if(posicao > 5 || posicao < 0){
            cores.setRed("Invalid Position");
            return null;
        } else {
            for (Item item : itens) {
                if (itens.indexOf(item) == posicao) {
                    i = item;
                    break;
                }
            }
        }
        return i;
    }

    //Método de equipar o item



    public void equiparItem(Item i) {
        for (Item item : itensEquipados) {
            if (item.getNome().equals(i.getNome())) {
                cores.setRed("Item already equipped.");
                return;
            } 

           if(p.getClasseCaractere() != i.getItemClasse()){
               cores.setRed("You can't equip this item.");
               return;
           }
           if(itensEquipados.size() == 3){
                cores.setRed("You can't equip more than 3 items.");
                return;
           }
           else {
                itensEquipados.add(i);
                p.setDano(p.getDano() + i.getDano());
                p.setArmadura(p.getArmadura() + i.getArmadura());
                p.setVida(p.getVida() + i.getVida());
                cores.setGreen(i.getNome() + " equipped.");
                return;
           }
        }
    }

    //Método que mostra o menu do inventário
    public void menuInventario(){
        if(itens.size() == 0){
            cores.setRed("Your inventory is empty.");
        } else {
        boolean running = true;
            while (running) {
                mostrarItens();
                System.out.println("[1]. Click here to select an item from your inventory");
                System.out.println("[2]. Exit");
                int opcao = input.nextInt();
                switch (opcao) {
                    case 1:
                        System.out.println("Insert the item's position:");
                        int posicao = input.nextInt();
                        Item i = getItem(posicao);
                        System.out.println("[1]. Equip item");
                        System.out.println("[2]. Consume Item");
                        System.out.println("[3]. Drop item");
                        System.out.println("[4]. Exit");
                        int opcao2 = input.nextInt();
                        switch (opcao2) {
                            case 1:
                            equiparItem(i);
                            break;
                            case 2:
                            removerItem(posicao);
                            break;
                            case 3:
                            break;
                            case 4:
                            System.out.println("Exiting...");
                            running = false;
                            break;
                            default:
                            System.out.println("Invalid option;");
                                break;
                        }
                        break;
                        case 2:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                    System.out.println("Invalid option");
                        break;
                }
            }
        }
    }
}
