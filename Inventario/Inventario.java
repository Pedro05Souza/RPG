package Inventario;
import Personagens.Personagem;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventario {
    private static final int MAXITENS = 5;
    private Personagem p;
    private Scanner input = new Scanner(System.in);
    protected int posAtual = -1;
    ArrayList<Item> itens = new ArrayList<Item>();
    
    // metodo que adiciona itens
    public void adicionarItem(Item i){
       if(itens.size() >= MAXITENS){
        System.out.println("Inventory is full");
       } 
       else {
        itens.add(i);
        posAtual = itens.size();
       }
    }

    // metodo que remove itens
    public void removerItem(int posicao){
        if(posicao > 5 || posicao < 0){
            System.out.println("Invalid Position");
        }
        for (Item item : itens) {
            if (itens.indexOf(item) == posicao) {
                itens.remove(item);
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
            System.out.println("Rarity: " + i.getTiposRaridade());
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
            System.out.println("Invalid Position");
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

    public void equiparItem(Item i){
       if(i.equipado == true){
        System.out.println("Item already equipped.");
       } 
       if(p.getClasseC() == i.getItemClasse()){
        i.equipado = true;
        p.setDano(p.getDano() + i.getDano());
        p.setArmadura(p.getArmadura() + i.getArmadura());
        p.setVida(p.getVida() + i.getVida());
        System.out.println("Item equipped.");
       } else {
        System.out.println("Item can't be equipped. Invalid class");
       }
    }

    public void menuInventario(){
        if(itens.size() == 0){
            System.out.println("Inventory is empty");
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
                System.out.println("[2]. Drop item");
                System.out.println("[3]. Exit");
                int opcao2 = input.nextInt();
                switch (opcao2) {
                    case 1:
                    equiparItem(i);
                    break;
                    case 2:
                    removerItem(posicao);
                    break;
                    case 3:
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
