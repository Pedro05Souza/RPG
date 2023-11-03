package Inventario;

public class Itens extends Inventario {
    protected String nome, descricao;
    protected tiposRaridade tiposRaridade;
    protected int dano, vida, armadura, valor;
    protected char itemClasse;
    boolean equipado;


    // enum para raridade dos itens
    public enum tiposRaridade{
        Common, Uncommon, Rare, Epic, Legendary, Mythic;
        
    }

    // impressão dos itens
    public void efeitosItem(){
        System.out.println("----------------------");
        System.out.println("Position: " + posAtual);
        System.out.println("Item name: " + getNome());
        System.out.println("Item Description: " + getDescricao());
        System.out.println("Damage: " + getDano());
        System.out.println("Armor: " + getArmadura());
        System.out.println("Health: " + getVida());
        System.out.println("Value: " + getValor());
        System.out.println("----------------------");
    }

    // diz a porcentagem de drop de item por raridade
    public int chanceRaridade() {
        int porcentagem = 0;
        switch (tiposRaridade) {
            case Common:
                porcentagem = 55;
                break;
            case Uncommon:
                porcentagem = 20;
                break;
            case Rare:
                porcentagem = 12;
                break;
            case Epic:
                porcentagem = 7;
                break;
            case Legendary:
                porcentagem = 5;
                break;
            case Mythic:
                porcentagem = 1;
                break;
            default:
                break;
        }
        return porcentagem;

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

    public tiposRaridade getTiposRaridade() {
        return tiposRaridade;
    }

    public void setTiposRaridade(tiposRaridade tiposRaridade) {
        this.tiposRaridade = tiposRaridade;
    }

    
    



    

    
}