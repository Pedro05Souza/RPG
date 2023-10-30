package Inventario;
import Personagens.Personagem;
import java.util.ArrayList;

public class Inventario {
    public static final int MAXITENS = 5;
    ArrayList<Itens> itens = new ArrayList<Itens>();

    public void adicionarItem(Itens i, Personagem p){
       if(itens.size() > MAXITENS){
        System.out.println("Inventory is full");
       } 
       if(p.getClasseC() != i.getItemClasse()){
        System.out.println("This item is not available for your class for your class.");
       }
       else {
        itens.add(i);
       }
    }

    public void removerItem(Itens i){
        itens.remove(i);
    }

    public void mostrarItens(){
        for(Itens i : itens){
            i.efeitosItem();
        }
    }
}
