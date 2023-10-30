package Inventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Itens {
    protected String nome, descricao;
    protected int dano, vida, armadura, valor;
    protected char itemClasse;
    boolean equipado;

    public void efeitosItem(){
        System.out.println("----------------------");
        System.out.println("Item name: " + getNome());
        System.out.println("Item Description: " + getDescricao());
        System.out.println("Damage: " + getDano());
        System.out.println("Armor: " + getArmadura());
        System.out.println("Health: " + getVida());
        System.out.println("Value: " + getValor());
        System.out.println("----------------------");
    }


    
    // getters e setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isEquipado() {
        return equipado;
    }
    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public int getDano() {
        return dano;
    }
    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getArmadura() {
        return armadura;
    }
    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public char getItemClasse() {
        return itemClasse;
    }
    public void setItemClasse(char itemClasse) {
        this.itemClasse = itemClasse;
    }

    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    



    

    
}