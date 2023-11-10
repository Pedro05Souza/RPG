package Inventario.Equipamentos;

import Inventario.Item;

/*
 * Item WarriorSword
 * STATUS: Falta Implementar
 */
public class WarriorSword extends Item {

    public WarriorSword(){
        nome = "Warrior Sword";
        itemClasse = 'G';
        descricao = "A sword made for warriors.";
        chanceRaridade = porcentagemPorRaridade(raridadeString = "Common");
        dano = 20;
        armadura = 0;
        vida = 0;
    }
    
}
