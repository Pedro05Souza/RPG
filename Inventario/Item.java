package Inventario;

/*
 * Superclasse de todos os tipos de itens
 * STATUS: Falta testar todos os métodos;
 */

public abstract class Item extends Inventario {
    protected String nome, descricao;
    protected int chanceRaridade;
    protected String raridadeString;
    protected int dano, vida, armadura, valor;
    protected char itemClasse;


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
        for(Item i : itensEquipados){
            if(i.getNome() == getNome()){
                System.out.println("EQUIPPED");
            }
        }
    }

    // diz a porcentagem de drop de item por raridade
    public int porcentagemPorRaridade(String raridade) {
        int porcentagem = 0;
        switch (raridade) {
            case "Common":
                porcentagem = 55;
                break;
            case "Uncommon":
                porcentagem = 20;
                break;
            case "Rare":
                porcentagem = 12;
                break;
            case "Epic":
                porcentagem = 7;
                break;
            case "Legendary":
                porcentagem = 5;
                break;
            case "Mythic":
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

    public int getChanceRaridade() {
        return chanceRaridade;
    }

    public void setChanceRaridade(int chanceRaridade) {
        this.chanceRaridade = chanceRaridade;
    }

    public String getRaridadeString() {
        return raridadeString;
    }

    public void setRaridade(String raridadeString) {
        this.raridadeString = raridadeString;
    }
    


}