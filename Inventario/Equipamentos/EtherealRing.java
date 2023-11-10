package Inventario.Equipamentos;
import Inventario.Item;
/*
 * Item Ethereal Ring
 * Status: Falta arrumar
 */
public class EtherealRing extends Item {

    public EtherealRing(){
        nome = "Ethereal Ring";
        itemClasse = 'M';
        descricao = "A ring imbued with the essence of the ethereal plane, crafted specifically for mages.";
        dano = 40;
        chanceRaridade = porcentagemPorRaridade(raridadeString = "Epic");
        armadura = 5;
        vida = 15;
    }
}
