package Inventario;

public class Itens {
    String nome, descricao;
    int efeito;
    boolean equipado;
    
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
    public int getEfeito() {
        return efeito;
    }
    public void setEfeito(int efeito) {
        this.efeito = efeito;
    }
    public boolean isEquipado() {
        return equipado;
    }
    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    
}