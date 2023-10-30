package Inventario;

import java.util.ArrayList;

public class Inventario {
    public static int MAXITENS = 5;
    ArrayList<Itens> itens = new ArrayList<Itens>();

    public void adicionarItem(Itens i){
        if(itens.size() < MAXITENS){
            itens.add(i);
        } else{
            System.out.println("Inventory is full");
        }
    }

    public void removerItem(Itens i){
        itens.remove(i);
    }

    public void mostrarItens(){
        for(Itens i : itens){
            System.out.println("Item name: "+ i.getNome() 
            + "Item Description: " + i.getDescricao() 
            + "Item Effects: " + i.getEfeito());
        }
    }
}
