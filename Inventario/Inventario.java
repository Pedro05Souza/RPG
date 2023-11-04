package Inventario;
import Personagens.Personagem;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventario {
    private static final int MAXITENS = 5;
    private Personagem p;
    private Scanner input = new Scanner(System.in);
    protected int posAtual = -1;
    ArrayList<Itens> itens = new ArrayList<Itens>();
    
    // metodo que adiciona itens
    public void adicionarItem(Itens i){
       if(itens.size() >= MAXITENS){
        System.out.println("Inventory is full");
       } 
       if(p.getClasseC() != i.getItemClasse()){
        System.out.println("This item is not available for your class.");
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
        for (Itens item : itens) {
            if (itens.indexOf(item) == posicao) {
                itens.remove(item);
                break;
            }
        }
    }

    // metodo que mostra os itens
    public void mostrarItens(){
        if(itens.size() == 0){
            System.out.println("Inventory is empty.");
        }
        else{
            for(Itens i : itens){
               i.efeitosItem();
            }
        }
    }

    // metodo que pega os itens do Inimigo que morreu
    public void pegaritemInimigo(Itens i){
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
    public Itens getItem(){
        System.out.println("Insert the position of the item: ");
        int posicao = input.nextInt();
        Itens i = null;
        if(posicao > 5 || posicao < 0){
            System.out.println("Invalid Position");
            return null;
        } else {
            for (Itens item : itens) {
                if (itens.indexOf(item) == posicao) {
                    i = item;
                    break;
                }
            }
        }
        return i;

    }

    public void equiparItem(){
        // falta fazer
    }

    public void menuInventario(){
        mostrarItens();
        System.out.println("[1]. Equip Item;");
        System.out.println("[2]. Remove Item;");
        System.out.println("[3]. Leave;");
        int opcao = input.nextInt();
        switch(opcao){
            case 1:
            Itens i = getItem();
            // fata fazer
        }
    }
}
