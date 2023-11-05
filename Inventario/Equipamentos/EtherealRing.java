package Inventario.Equipamentos;

import Personagens.Mago;
import Inventario.Item;

public class EtherealRing extends Item {
    private static final Mago Personagem = null;
    private int mana;

    public EtherealRing(){
        nome = "Ethereal Ring";
        itemClasse = 'M';
        descricao = "A ring imbued with the essence of the ethereal plane, crafted specifically for mages.";
        dano = 40;
        tiposRaridade = Inventario.Item.tiposRaridade.Epic;
        armadura = 5;
        vida = 15;
        mana = (int) (.3 * ((Mago) Personagem).getManaTotal());
    }


    
}
